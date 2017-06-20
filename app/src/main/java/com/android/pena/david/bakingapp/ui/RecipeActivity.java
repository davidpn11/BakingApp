package com.android.pena.david.bakingapp.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.android.pena.david.bakingapp.Model.Recipe;
import com.android.pena.david.bakingapp.Model.Step;
import com.android.pena.david.bakingapp.R;
import com.android.pena.david.bakingapp.adapter.StepAdapter;

import butterknife.BindView;

public class RecipeActivity extends AppCompatActivity implements RecipeFragment.onStepClickListener,
        StepFragment.onChangeStepListener{

    private static final String RECIPE_TAG = "recipe_id";
    private Recipe mRecipe;
    private int num_steps;
    private FragmentManager fragmentManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        fragmentManager = getSupportFragmentManager();
        int recipe_id = getIntent().getIntExtra(RECIPE_TAG,-1);
        mRecipe = MainActivity.getRecipe(recipe_id);
        setTitle(mRecipe.getName());
        num_steps = mRecipe.getSteps().size();

        if(getResources().getBoolean(R.bool.isTablet)) {

        }else{
            RecipeFragment recipeFragment = RecipeFragment.newInstance(mRecipe);
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_layout, recipeFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStepClicked(Step step) {
        StepFragment stepFrag = StepFragment.newInstance(step,num_steps);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_layout,stepFrag).addToBackStack(null)
                .commit();
    }

    @Override
    public void nextStep(int id) {
        Step nextStep = mRecipe.getSteps().get(id+1);
        StepFragment stepFrag = StepFragment.newInstance(nextStep,num_steps);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_layout,stepFrag).addToBackStack(null)
                .commit();
    }

    @Override
    public void previousStep(int id) {
        Step nextStep = mRecipe.getSteps().get(id-1);
        StepFragment stepFrag = StepFragment.newInstance(nextStep,num_steps);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_layout,stepFrag).addToBackStack(null)
                .commit();

    }
}
