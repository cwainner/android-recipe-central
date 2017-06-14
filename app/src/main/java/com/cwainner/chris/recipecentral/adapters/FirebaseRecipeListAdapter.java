package com.cwainner.chris.recipecentral.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.cwainner.chris.recipecentral.models.Recipe;
import com.cwainner.chris.recipecentral.util.ItemTouchHelperAdapter;
import com.cwainner.chris.recipecentral.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


public class FirebaseRecipeListAdapter extends FirebaseRecyclerAdapter<Recipe, FirebaseRecipeViewHolder> implements ItemTouchHelperAdapter{

    private DatabaseReference databaseReference;
    private OnStartDragListener onStartDragListener;
    private Context context;

    public FirebaseRecipeListAdapter(Class<Recipe> modelClass, int modelLayout,
                                     Class<FirebaseRecipeViewHolder> viewHolderClass,
                                     Query ref, OnStartDragListener onStartDragListener, Context context){
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.databaseReference = ref.getRef();
        this.onStartDragListener = onStartDragListener;
        this.context = context;
    }

    @Override
    protected void populateViewHolder(FirebaseRecipeViewHolder viewHolder, Recipe model, int position) {
        viewHolder.bindRecipe(model);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
