package com.android.pena.david.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pena.david.bakingapp.Model.Recipe;
import com.android.pena.david.bakingapp.R;
import com.android.pena.david.bakingapp.ui.RecipeActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by david on 04/06/17.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<Recipe> recipesArray;
    private Context mContext;

    public RecipesAdapter(Context pContext, List<Recipe> recipes){
        setHasStableIds(true);
        this.recipesArray = recipes;
        this.mContext = pContext;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_cardview,null);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindRecipeData(recipesArray.get(position));

    }

    @Override
    public int getItemCount() {
        return recipesArray.size();
    }

    public void updateData(List<Recipe> recipes){
        recipesArray = recipes;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.recipe_image) ImageView recipeImage;
        @BindView(R.id.recipe_name) TextView recipeName;
        @BindView(R.id.recipe_servings) TextView recipeServings;
        @BindView(R.id.recipe_card) CardView recipeView;
        private Recipe recipe;
        private final String RECIPE_TAG = "recipe_id";

        private ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            recipeView.setOnClickListener(this);
        }

        private void bindRecipeData(Recipe recipe){
            this.recipe = recipe;
            recipeName.setText(recipe.getName());
            String nServings = recipe.getServings()+" "+ mContext.getResources().getString(R.string.recipe_servings);
            recipeServings.setText(nServings);

            if(!recipe.getImage().isEmpty()){
                Picasso.with(mContext).load(recipe.getImage()).into(recipeImage);
            }
        }

        @Override
        public void onClick(View v) {
            Intent it = new Intent(v.getContext(), RecipeActivity.class);
            it.putExtra(RECIPE_TAG,recipe.getId());
            mContext.startActivity(it);
        }
    }
}
