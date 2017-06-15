package com.cwainner.chris.recipecentral.ui;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cwainner.chris.recipecentral.Constants;
import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.models.Recipe;

import org.parceler.Parcels;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipes = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_RECIPES));
        position = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);

        RecipeDetailFragment recipeDetailFragment = RecipeDetailFragment.newInstance(recipes, position);

        FragmentTransaction fragmentTransaction = ((FragmentActivity) this).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.recipeDetailFrameLayout, recipeDetailFragment);
        fragmentTransaction.commit();
    }
}
