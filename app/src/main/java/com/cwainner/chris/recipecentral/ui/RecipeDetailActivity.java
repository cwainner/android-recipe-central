package com.cwainner.chris.recipecentral.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class RecipeDetailActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.recipeDetailHeader) TextView recipeDetailHeader;
    @Bind(R.id.recipeDetailBody) TextView recipeDetailBody;
    @Bind(R.id.recipeListUrl) TextView recipeUrl;
    @Bind(R.id.saveRecipeButton) Button recipeSaveButton;
    @Bind(R.id.recipeImage) ImageView recipeImage;
    ArrayList<Recipe> recipes = new ArrayList<>();

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        recipes = Parcels.unwrap(getIntent().getParcelableExtra("recipes"));
        int position = getIntent().getIntExtra("position", 0);
        recipe = recipes.get(position);

        if(!recipe.getThumbnail().isEmpty()){
            Picasso.with(this).load(recipe.getThumbnail())
                    .resize(500, 500)
                    .centerCrop()
                    .into(recipeImage);
        }

        recipeDetailHeader.setText(recipe.getTitle());
        recipeUrl.setText(recipe.getRecipeId());
        recipeDetailBody.setText(android.text.TextUtils.join(", ", recipe.getIngredients()));

        recipeSaveButton.setOnClickListener(this);
        recipeUrl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == recipeSaveButton){
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
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        if(v == recipeUrl){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getRecipeId()));
            startActivity(intent);
        }
    }
}
