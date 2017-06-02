package com.cwainner.chris.recipecentral.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.services.RecipeService;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipesActivity extends AppCompatActivity {
    private static final String TAG = RecipesActivity.class.getSimpleName();
    @Bind(R.id.recipesHeader) TextView recipesHeader;
    @Bind(R.id.recipeTypeView) TextView recipeTypeView;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        // Get Parameters from intent
        Intent intent = getIntent();
        String recipeType = intent.getStringExtra("recipeType");
        String ingredients = intent.getStringExtra("ingredients");
        recipeTypeView.setText(recipeType + " with " + ingredients);

        // Set header font
        Typeface quicksandFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.otf");
        recipesHeader.setTypeface(quicksandFont);

        // Get recipes from API
        getRecipes(recipeType, ingredients);

    }

    private void getRecipes(String recipeType, String ingredients){
        final RecipeService recipeService = new RecipeService();
        recipeService.findRecipes(recipeType, ingredients, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                recipeService.processResults(response);
            }
        });
    }
}
