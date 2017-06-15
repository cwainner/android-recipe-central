package com.cwainner.chris.recipecentral.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.models.Recipe;
import com.cwainner.chris.recipecentral.ui.RecipeDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private Context context;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.RecipeViewHolder holder, int position){
        holder.bindRecipe(recipes.get(position));
    }

    @Override
    public int getItemCount(){
        return recipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.recipeListImage) ImageView recipeListImage;
        @Bind(R.id.recipeListTitle) TextView recipeListTitle;
        @Bind(R.id.recipeListIngredients) TextView recipeListIngredients;

        private Context viewHolderContext;

        public RecipeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            viewHolderContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindRecipe(Recipe recipe){
            recipeListTitle.setText(recipe.getTitle());
            recipeListIngredients.setText(android.text.TextUtils.join(", ", recipe.getIngredients()));
            if(!recipe.getThumbnail().isEmpty()){
                Picasso.with(viewHolderContext).load(recipe.getThumbnail())
                        .resize(100,100)
                        .centerCrop()
                        .into(recipeListImage);
            }
        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("position", itemPosition);
            intent.putExtra("recipes", Parcels.wrap(recipes));
            context.startActivity(intent);
        }
    }


}
