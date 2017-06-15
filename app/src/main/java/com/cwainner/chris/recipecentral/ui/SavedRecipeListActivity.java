package com.cwainner.chris.recipecentral.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.cwainner.chris.recipecentral.Constants;
import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.adapters.FirebaseRecipeListAdapter;
import com.cwainner.chris.recipecentral.adapters.FirebaseRecipeViewHolder;
import com.cwainner.chris.recipecentral.models.Recipe;
import com.cwainner.chris.recipecentral.util.OnStartDragListener;
import com.cwainner.chris.recipecentral.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRecipeListActivity extends AppCompatActivity implements OnStartDragListener{
    private DatabaseReference recipeReference;
    private FirebaseRecipeListAdapter firebaseAdapter;
    private ItemTouchHelper itemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        recipeReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_RECIPES)
                .child(uid);

        firebaseAdapter = new FirebaseRecipeListAdapter(Recipe.class, R.layout.recipe_list_item_drag, FirebaseRecipeViewHolder.class, recipeReference, this, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(firebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(firebaseAdapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        firebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        itemTouchHelper.startDrag(viewHolder);
    }
}
