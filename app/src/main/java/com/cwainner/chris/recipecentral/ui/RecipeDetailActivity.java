package com.cwainner.chris.recipecentral.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.adapters.RecipePagerAdapter;
import com.cwainner.chris.recipecentral.models.Recipe;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity {
    @Bind(R.id.recipeDetailFrameLayout) FrameLayout frameLayout;
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
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("recipe", Parcels.wrap(recipes.get(startingPosition)));
        recipeDetailFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.recipeDetailFrameLayout, recipeDetailFragment);
        fragmentTransaction.commit();
    }
}
