package com.cwainner.chris.recipecentral.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cwainner.chris.recipecentral.Constants;
import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.adapters.RecipeListAdapter;
import com.cwainner.chris.recipecentral.models.Recipe;
import com.cwainner.chris.recipecentral.services.RecipeService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeListActivity extends AppCompatActivity {

    public static final String TAG = RecipeListActivity.class.getSimpleName();

    @Bind(R.id.recipesHeader) TextView recipesHeader;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    private RecipeListAdapter adapter;
    private DatabaseReference searchedReference;
    private SharedPreferences sharedPreferences;
    private String searched;

    public ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        searchedReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        // Get Parameters from intent
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        searched = sharedPreferences.getString(Constants.PREFERENCES_SEARCHED_KEY, null);

        // Set header font
        Typeface quicksandFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.otf");
        recipesHeader.setTypeface(quicksandFont);

        // Get recipes from API
        getRecipes(searched);

    }

    private void getRecipes(String ingredients){
        final RecipeService recipeService = new RecipeService();
        recipeService.findRecipes(ingredients, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                recipes = recipeService.processResults(response);

                RecipeListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new RecipeListAdapter(getApplicationContext(), recipes);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(RecipeListActivity.this));
                        recyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
