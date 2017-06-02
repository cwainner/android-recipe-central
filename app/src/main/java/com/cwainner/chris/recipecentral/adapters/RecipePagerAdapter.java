package com.cwainner.chris.recipecentral.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cwainner.chris.recipecentral.models.Recipe;
import com.cwainner.chris.recipecentral.ui.RecipeDetailFragment;

import java.util.ArrayList;

public class RecipePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Recipe> recipes;

    public RecipePagerAdapter(FragmentManager fm, ArrayList<Recipe> recipes){
        super(fm);
        this.recipes = recipes;
    }

    @Override
    public Fragment getItem(int position){
        return RecipeDetailFragment.newInstance(recipes.get(position));
    }

    @Override
    public int getCount(){
        return recipes.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return recipes.get(position).getTitle();
    }
}
