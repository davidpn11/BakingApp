package com.android.pena.david.bakingapp.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.pena.david.bakingapp.Model.Recipe;
import com.android.pena.david.bakingapp.R;

public class RecipeActivity extends AppCompatActivity {

    private final String RECIPE_TAG = "recipe_id";
    Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        int recipe_id = getIntent().getIntExtra(RECIPE_TAG,-1);
        mRecipe = MainActivity.getRecipe(recipe_id);

        if(findViewById(R.id.step_fragment) == null ) {
            Toast.makeText(this, "mobile", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "tablet", Toast.LENGTH_SHORT).show();
        }


       // RecipeFragment recipeFragment = new RecipeFragment();
//        StepFragment stepFragment = new StepFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(R.id.fragment_layout, stepFragment)
//                .commit();
    }
}
