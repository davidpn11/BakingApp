package com.android.pena.david.bakingapp.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.pena.david.bakingapp.Model.Recipe;
import com.android.pena.david.bakingapp.R;

import butterknife.BindView;

public class RecipeActivity extends AppCompatActivity {

    private final String RECIPE_TAG = "recipe_id";
    private Recipe mRecipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        int recipe_id = getIntent().getIntExtra(RECIPE_TAG,-1);
        mRecipe = MainActivity.getRecipe(recipe_id);

        if(getResources().getBoolean(R.bool.isTablet)) {

        }else{
            RecipeFragment recipeFragment = RecipeFragment.newInstance(mRecipe);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_layout, recipeFragment)
                    .commit();

        }
    }
}
