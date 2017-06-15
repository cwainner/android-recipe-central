package com.cwainner.chris.recipecentral.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cwainner.chris.recipecentral.Constants;
import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.models.Recipe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.recipeDetailHeader) TextView recipeDetailHeader;
    @Bind(R.id.recipeDetailBody) TextView recipeDetailBody;
    @Bind(R.id.recipeListUrl) TextView recipeUrl;
    @Bind(R.id.saveRecipeButton) Button recipeSaveButton;
    @Bind(R.id.recipeImage) ImageView recipeImage;

    private Recipe recipe;
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private int position;

    public static RecipeDetailFragment newInstance(ArrayList<Recipe> recipes, Integer position) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(recipes));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        ButterKnife.bind(this, view);

        recipes = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_RECIPES));
        position = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        recipe = recipes.get(position);

        if(!recipe.getThumbnail().isEmpty()){
            Picasso.with(getActivity()).load(recipe.getThumbnail())
                    .resize(500, 500)
                    .centerCrop()
                    .into(recipeImage);
        }

        recipeDetailHeader.setText(recipe.getTitle());
//        recipeUrl.setText(recipe.getRecipeId());
        recipeDetailBody.setText(android.text.TextUtils.join(", ", recipe.getIngredients()));

        recipeSaveButton.setOnClickListener(this);
//        recipeUrl.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == recipeSaveButton){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference recipeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RECIPES)
                    .child(uid);
            DatabaseReference pushRef = recipeRef.push();
            String pushId = pushRef.getKey();
            recipe.setPushId(pushId);
            pushRef.setValue(recipe);
            Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
        }
//        if(view == recipeUrl){
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getRecipeId()));
//            startActivity(intent);
//        }
    }
}
