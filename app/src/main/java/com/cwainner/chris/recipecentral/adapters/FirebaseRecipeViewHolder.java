package com.cwainner.chris.recipecentral.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwainner.chris.recipecentral.Constants;
import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.models.Recipe;
import com.cwainner.chris.recipecentral.ui.RecipeDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    View view;
    Context context;
    public ImageView dragIcon;

    public FirebaseRecipeViewHolder(View itemView){
        super(itemView);
        this.view = itemView;
        this.context = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindRecipe(Recipe recipe){
        dragIcon = (ImageView) view.findViewById(R.id.dragIcon);
        ImageView recipeImageView = (ImageView) view.findViewById(R.id.recipeListImage);
        TextView nameTextView = (TextView) view.findViewById(R.id.recipeListTitle);
        TextView recipeListIngredients = (TextView) view.findViewById(R.id.recipeListIngredients);

        Picasso.with(context)
                .load(recipe.getThumbnail())
                .resize(100, 100)
                .centerCrop()
                .into(recipeImageView);

        nameTextView.setText(recipe.getTitle());
        recipeListIngredients.setText(android.text.TextUtils.join(", ", recipe.getIngredients()));
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Recipe> recipes = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPES).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    recipes.add(snapshot.getValue(Recipe.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("recipes", Parcels.wrap(recipes));
                context.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
