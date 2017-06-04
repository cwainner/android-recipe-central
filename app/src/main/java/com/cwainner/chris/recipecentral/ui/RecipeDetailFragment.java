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

import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.models.Recipe;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecipeDetailFragment extends Fragment  implements  View.OnClickListener{
    @Bind(R.id.recipeDetailHeader) TextView recipeDetailHeader;
    @Bind(R.id.recipeDetailBody) TextView recipeDetailBody;
    @Bind(R.id.recipeListUrl) TextView recipeUrl;
    @Bind(R.id.recipeDetailCloseButton) Button recipeDetailCloseButton;
    @Bind(R.id.recipeImage) ImageView recipeImage;

    private Recipe recipe;

    public static RecipeDetailFragment newInstance(Recipe recipe){
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", Parcels.wrap(recipe));
        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        recipe = Parcels.unwrap(getArguments().getParcelable("recipe"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        if(!recipe.getThumbnail().isEmpty()){
            Picasso.with(view.getContext()).load(recipe.getThumbnail())
                    .resize(500, 500)
                    .centerCrop()
                    .into(recipeImage);
        }

        recipeDetailHeader.setText(recipe.getTitle());
        recipeUrl.setText(recipe.getHref());
        recipeDetailBody.setText(recipe.getIngredients());

        recipeDetailCloseButton.setOnClickListener(this);
        recipeUrl.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        if(v == recipeDetailCloseButton){
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);
        }
        if(v == recipeUrl){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getHref()));
            startActivity(intent);
        }
    }
}
