package com.cwainner.chris.recipecentral.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.cwainner.chris.recipecentral.Constants;
import com.cwainner.chris.recipecentral.models.Recipe;
import com.cwainner.chris.recipecentral.ui.RecipeDetailActivity;
import com.cwainner.chris.recipecentral.util.ItemTouchHelperAdapter;
import com.cwainner.chris.recipecentral.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;


public class FirebaseRecipeListAdapter extends FirebaseRecyclerAdapter<Recipe, FirebaseRecipeViewHolder> implements ItemTouchHelperAdapter{

    private DatabaseReference databaseReference;
    private OnStartDragListener onStartDragListener;
    private Context context;
    private ChildEventListener childEventListener;
    private ArrayList<Recipe> recipes = new ArrayList<>();

    public FirebaseRecipeListAdapter(Class<Recipe> modelClass, int modelLayout,
                                     Class<FirebaseRecipeViewHolder> viewHolderClass,
                                     Query ref, OnStartDragListener onStartDragListener, Context context){
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.databaseReference = ref.getRef();
        this.onStartDragListener = onStartDragListener;
        this.context = context;

        childEventListener = ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                recipes.add(dataSnapshot.getValue(Recipe.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void cleanup(){
        super.cleanup();
        setIndexInFirebase();
        databaseReference.removeEventListener(childEventListener);
    }

    @Override
    protected void populateViewHolder(final FirebaseRecipeViewHolder viewHolder, Recipe model, final int position) {
        viewHolder.bindRecipe(model);
        viewHolder.dragIcon.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    onStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("recipes", Parcels.wrap(recipes));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        Collections.swap(recipes, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        recipes.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase(){
        for(Recipe recipe : recipes){
            int index = recipes.indexOf(recipe);
            DatabaseReference ref = getRef(index);
            recipe.setIndex(Integer.toString(index));
            ref.setValue(recipe);
        }
    }
}
