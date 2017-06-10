package com.cwainner.chris.recipecentral.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cwainner.chris.recipecentral.Constants;
import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.adapters.FirebaseRecipeViewHolder;
import com.cwainner.chris.recipecentral.models.Recipe;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRecipeListActivity extends AppCompatActivity {
    private DatabaseReference recipeReference;
    private FirebaseRecyclerAdapter firebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        recipeReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        firebaseAdapter = new FirebaseRecyclerAdapter<Recipe, FirebaseRecipeViewHolder>
                (Recipe.class, R.layout.recipe_list_item, FirebaseRecipeViewHolder.class, recipeReference) {
            @Override
            protected void populateViewHolder(FirebaseRecipeViewHolder viewHolder, Recipe model, int position) {
                viewHolder.bindRecipe(model);
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(firebaseAdapter);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        firebaseAdapter.cleanup();
    }
}
