package com.cwainner.chris.recipecentral.ui;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.adapters.RecipesArrayAdapter;
import com.cwainner.chris.recipecentral.services.RecipeService;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipesActivity extends AppCompatActivity {
    private static final String TAG = RecipesActivity.class.getSimpleName();
    @Bind(R.id.recipesHeader) TextView recipesHeader;
    @Bind(R.id.recipeTypeView) TextView recipeTypeView;
    @Bind(R.id.recipeGrid) GridView recipeGrid;

    private String[] recipes = new String[] {"Brownies", "Nachos", "Alfredo", "Chili", "Banana Bread", "Burger", "Cheesecake", "Ice Cream"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String recipeType = intent.getStringExtra("recipeType");
        String ingredients = intent.getStringExtra("ingredients");
        recipeTypeView.setText(recipeType + " with " + ingredients);

        recipeGrid.setAdapter(new RecipesArrayAdapter(this, android.R.layout.simple_list_item_1, recipes));
        recipeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String recipe = ((TextView)view).getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("recipe", recipe);
                FragmentManager fm = getFragmentManager();
                RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
                recipeDetailFragment.setArguments(bundle);
                recipeDetailFragment.show(fm, "Sample Fragment");
            }
        });

        Typeface quicksandFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Bold.otf");
        recipesHeader.setTypeface(quicksandFont);

        getRecipes(recipeType, ingredients);
    }

    private void getRecipes(String recipeType, String ingredients){
        final RecipeService recipeService = new RecipeService();
        recipeService.findRecipes(recipeType, ingredients, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
