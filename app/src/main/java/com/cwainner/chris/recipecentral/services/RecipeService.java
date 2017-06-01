package com.cwainner.chris.recipecentral.services;

import com.cwainner.chris.recipecentral.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by chris on 01/06/17.
 */

public class RecipeService {

    public static void findRecipes(String recipeType, String ingredients, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.INGREDIENT_QUERY_PARAMETER, ingredients);
        urlBuilder.addQueryParameter(Constants.QUERY_PARAMETER, recipeType);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
