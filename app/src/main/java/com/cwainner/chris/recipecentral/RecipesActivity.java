package com.cwainner.chris.recipecentral;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipesActivity extends AppCompatActivity {
    @Bind(R.id.recipesHeader) TextView recipesHeader;
    @Bind(R.id.recipeTypeView) TextView recipeTypeView;
    @Bind(R.id.recipeGrid) GridView recipeGrid;

    private String[] recipes = new String[] {"Brownies", "Nachos", "Alfredo", "Chili", "Banana Bread", "Burger", "Cheesecake", "Ice Cream"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        RecipesArrayAdapter adapter = new RecipesArrayAdapter(this, android.R.layout.simple_list_item_1, recipes);
        recipeGrid.setAdapter(adapter);

        Intent intent = getIntent();
        String recipeType = intent.getStringExtra("recipeType");
        recipeTypeView.setText("Recipe type: " + recipeType);

        Typeface quicksandFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.otf");
        recipesHeader.setTypeface(quicksandFont);
    }
}
