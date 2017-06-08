package com.cwainner.chris.recipecentral.services;

import android.net.Uri;
import android.util.Log;

import com.cwainner.chris.recipecentral.Constants;
import com.cwainner.chris.recipecentral.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chris on 01/06/17.
 */

public class RecipeService {

    public static void findRecipes(String ingredients, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_ID_PARAMETER, Constants.YUMMLY_ID);
        urlBuilder.addQueryParameter(Constants.API_KEY_PARAMETER, Constants.YUMMLY_KEY);
        urlBuilder.addQueryParameter(Constants.QUERY_PARAMETER, ingredients);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Recipe> processResults(Response response){
        ArrayList<Recipe> recipes = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject recipeListJSON = new JSONObject(jsonData);
                JSONArray recipeArrayJSON = recipeListJSON.getJSONArray("matches");
                for(int i = 0; i < recipeArrayJSON.length(); i++){
                    JSONObject recipeJSON = recipeArrayJSON.getJSONObject(i);
                    String title = recipeJSON.getString("recipeName");
                    String recipeId = recipeJSON.getString("id");
                    ArrayList<String> ingredients = new ArrayList<>();
                    JSONArray ingredientsJSONArray = recipeJSON.getJSONArray("ingredients");
                    for(int j = 0; j < ingredientsJSONArray.length(); j++){
                        ingredients.add(ingredientsJSONArray.get(j).toString());
                    }
                    String thumbnail = recipeJSON.getJSONObject("imageUrlsBySize").getString("90");

                    Recipe recipe = new Recipe(title, ingredients, thumbnail, recipeId);
                    recipes.add(recipe);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }

        return recipes;
    }
}
