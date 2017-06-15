package com.cwainner.chris.recipecentral.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Recipe {
    private String title;
    private ArrayList<String> ingredients;
    private String thumbnail;
    private String recipeId;
    private String pushId;
    private String index;

    public Recipe(){}

    public Recipe(String title, ArrayList<String> ingredients, String thumbnail, String recipeId) {
        this.title = title;
        this.ingredients = ingredients;
        this.thumbnail = thumbnail;
        this.recipeId = recipeId;
        this.index = "not_specified";
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

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
