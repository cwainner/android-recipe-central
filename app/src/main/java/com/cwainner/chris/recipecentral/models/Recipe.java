package com.cwainner.chris.recipecentral.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Recipe {
    private String title;
    private ArrayList<String> ingredients;
    private String thumbnail;
    private String recipeId;

    public Recipe(){}

    public Recipe(String title, ArrayList<String> ingredients, String thumbnail, String recipeId) {
        this.title = title;
        this.ingredients = ingredients;
        this.thumbnail = thumbnail;
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getRecipeId() {
        return recipeId;
    }
}
