package com.android.pena.david.bakingapp.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.pena.david.bakingapp.Model.Recipe;
import com.android.pena.david.bakingapp.Model.Step;
import com.android.pena.david.bakingapp.R;
import com.android.pena.david.bakingapp.adapter.StepAdapter;

import butterknife.BindView;

public class RecipeActivity extends AppCompatActivity implements RecipeFragment.onStepClickListener {

    private final String RECIPE_TAG = "recipe_id";
    private Recipe mRecipe;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        fragmentManager = getSupportFragmentManager();

        int recipe_id = getIntent().getIntExtra(RECIPE_TAG,-1);
        mRecipe = MainActivity.getRecipe(recipe_id);

        if(getResources().getBoolean(R.bool.isTablet)) {

        }else{
            RecipeFragment recipeFragment = RecipeFragment.newInstance(mRecipe);
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_layout, recipeFragment)
                    .commit();

        }
    }

    @Override
    public void onStepClicked(Step step) {
//        Toast.makeText(this, step.getShortDescription(), Toast.LENGTH_SHORT).show();
        StepFragment stepFrag = new StepFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_layout,stepFrag)
                .commit();


    }
}
