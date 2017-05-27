package com.cwainner.chris.recipecentral;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by Chris on 5/26/2017.
 */

public class RecipesArrayAdapter extends ArrayAdapter {
    private Context context;
    private String[] recipes;

    public RecipesArrayAdapter(Context context, int resource, String[] recipes){
        super(context, resource);
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public Object getItem(int position){
        String recipe = recipes[position];
        return recipe;
    }

    @Override
    public int getCount(){
        return recipes.length;
    }
}
