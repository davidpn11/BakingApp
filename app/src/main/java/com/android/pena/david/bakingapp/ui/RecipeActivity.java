package com.android.pena.david.bakingapp.ui;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.android.pena.david.bakingapp.Model.Recipe;
import com.android.pena.david.bakingapp.Model.Step;
import com.android.pena.david.bakingapp.R;


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

        startActivityValues();

        if(getResources().getBoolean(R.bool.isTablet)) {

            RecipeFragment recipeFragment = RecipeFragment.newInstance(mRecipe);
            fragmentManager.beginTransaction()
                    .add(R.id.list_fragment, recipeFragment)
                    .commit();

        }else{
            RecipeFragment recipeFragment = RecipeFragment.newInstance(mRecipe);
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_layout, recipeFragment).addToBackStack(null)
                    .commit();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fragmentManager.getBackStackEntryCount()==0){
            finish();
        }
    }

    @Override
    public void onStepClicked(Step step) {

        if(!getResources().getBoolean(R.bool.isTablet)) {
            StepFragment stepFrag = StepFragment.newInstance(step,num_steps);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout,stepFrag)
                    .addToBackStack(null)
                    .commit();
        }else {
            StepFragment stepFrag = StepFragment.newInstance(step,num_steps);
            fragmentManager.beginTransaction()
                    .replace(R.id.step_fragment,stepFrag)
                    .commit();
        }

    }

    @Override
    public void nextStep(int id) {

        Step nextStep = mRecipe.getSteps().get(id+1);
        if(!getResources().getBoolean(R.bool.isTablet)) {
            StepFragment stepFrag = StepFragment.newInstance(nextStep,num_steps);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout,stepFrag)
                    .addToBackStack(null)
                    .commit();
        }else{
            StepFragment stepFrag = StepFragment.newInstance(nextStep,num_steps);
            fragmentManager.beginTransaction()
                    .replace(R.id.step_fragment,stepFrag)
                    .commit();


        }

    }

    @Override
    public void previousStep(int id) {

        Step previousStep = mRecipe.getSteps().get(id - 1);
        if(!getResources().getBoolean(R.bool.isTablet)) {
            StepFragment stepFrag = StepFragment.newInstance(previousStep, num_steps);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout, stepFrag)
                    .addToBackStack(null)
                    .commit();
        }else{
            StepFragment stepFrag = StepFragment.newInstance(previousStep, num_steps);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout, stepFrag)
                    .commit();

        }

    }


    private void startActivityValues(){

        //Set recipe and number of steps
        int recipe_id = getIntent().getIntExtra(RECIPE_TAG,-1);
        mRecipe = MainActivity.getRecipe(recipe_id);
        num_steps = mRecipe.getSteps().size();


        //set ActionBar and FragmentManager
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(mRecipe.getName());
        fragmentManager = getSupportFragmentManager();
    }
}
