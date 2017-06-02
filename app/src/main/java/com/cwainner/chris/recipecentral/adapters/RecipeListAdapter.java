package com.cwainner.chris.recipecentral.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwainner.chris.recipecentral.R;
import com.cwainner.chris.recipecentral.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Chris on 6/2/2017.
 */

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

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recipeListImage) ImageView recipeListImage;
        @Bind(R.id.recipeListTitle) TextView recipeListTitle;

        private Context viewHolderContext;

        public RecipeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            viewHolderContext = itemView.getContext();
        }

        public void bindRecipe(Recipe recipe){
            recipeListTitle.setText(recipe.getTitle());
            if(!recipe.getThumbnail().isEmpty()){
                Picasso.with(viewHolderContext).load(recipe.getThumbnail())
                        .resize(130,100)
                        .into(recipeListImage);
            }
        }
    }


}
