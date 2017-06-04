package com.cwainner.chris.recipecentral.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.adapters.RecipePagerAdapter;
import com.cwainner.chris.recipecentral.models.Recipe;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager viewPager;
    private RecipePagerAdapter adapterViewPager;
    ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        recipes = Parcels.unwrap(getIntent().getParcelableExtra("recipes"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new RecipePagerAdapter(getSupportFragmentManager(), recipes);
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(startingPosition);
    }
}
