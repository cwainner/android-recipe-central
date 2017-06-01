package com.cwainner.chris.recipecentral.models;

/**
 * Created by chris on 01/06/17.
 */

public class Recipe {
    private String title;
    private String ingredients;
    private String thumbnail;
    private String href;

    public Recipe(String title, String ingredients, String thumbnail, String href) {
        this.title = title;
        this.ingredients = ingredients;
        this.thumbnail = thumbnail;
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getHref() {
        return href;
    }
}
