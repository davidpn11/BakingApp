package com.android.pena.david.bakingapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.pena.david.bakingapp.Model.Ingredient;
import com.android.pena.david.bakingapp.Model.Recipe;
import com.android.pena.david.bakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by david on 01/06/17.
 */

public class RecipeFragment extends Fragment {

    @BindView(R.id.ingredients_list) TextView ingredients_list;

    private static final String RECIPE_PARCELABLE = "recipe_parcelable";
    private Recipe recipe;

    public RecipeFragment(){
    }

    public static RecipeFragment newInstance(Recipe recipe){
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putParcelable(RECIPE_PARCELABLE,recipe);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            recipe = getArguments().getParcelable(RECIPE_PARCELABLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_fragment,container,false);
        ButterKnife.bind(this,view);

        ingredients_list.setText(buildIngredientsCard(recipe.getIngredients()));
        return view;
    }

    private String buildIngredientsCard(List<Ingredient> list){

        StringBuilder stringBuilder = new StringBuilder();

        for(Ingredient ingredient : list){
            stringBuilder.append("\u2022 ")
                         .append(ingredient.getQuantity())
                         .append(" ")
                         .append(ingredient.getMeasure())
                         .append(" of ")
                         .append(ingredient.getIngredient())
                         .append("\n");
        }
        return stringBuilder.toString();
    }
}
