package com.android.pena.david.bakingapp.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.android.pena.david.bakingapp.Model.Recipe;
import com.android.pena.david.bakingapp.R;
import com.android.pena.david.bakingapp.Utils.ApiClient;
import com.android.pena.david.bakingapp.Utils.ApiInterface;
import com.android.pena.david.bakingapp.adapter.RecipesAdapter;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String WIDGET_RECIPE = "widget_recipe";
    private static final String WIDGET_ACTION = "ACTION_GET_RECIPE";
    private static final String RECIPE_TAG = "recipe_id";

    @BindView(R.id.recipes_list) RecyclerView recipeRecyclerView;
    public static List<Recipe> recipesList;
    private RecipesAdapter recipesAdapter;



    public static Recipe getRecipe(int id){
        return recipesList.get(id-1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        getRecipesfromAPI();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(getIntent().getAction().equals(WIDGET_ACTION)){
            Toast.makeText(this, "veio do widget:  "+getIntent().getIntExtra(WIDGET_RECIPE,-1), Toast.LENGTH_LONG).show();
            widgetRecipe(getIntent().getIntExtra(WIDGET_RECIPE,-1));

        }
    }


    private void widgetRecipe(int recipe_id){

        if (recipe_id > 0) {
            Intent it = new Intent(MainActivity.this, RecipeActivity.class);
            it.putExtra(RECIPE_TAG,recipe_id);
            startActivity(it);
        }
    }

    private void getRecipesfromAPI(){


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Recipe>> apiCall = apiService.getRecipes();

        apiCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

                progressDialog.dismiss();
                recipesList = response.body();
                //LinearLayoutManager linear = new LinearLayoutManager(getApplicationContext());
                GridLayoutManager grid = new GridLayoutManager(getApplicationContext(),1);
                recipeRecyclerView.setLayoutManager(getGridLayoutManager());
                recipesAdapter = new RecipesAdapter(getApplicationContext(),recipesList);
                recipeRecyclerView.setAdapter(recipesAdapter);
            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private GridLayoutManager getGridLayoutManager(){
        int nColumns;
        if(getResources().getBoolean(R.bool.isTablet)){
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
               nColumns = 3;
            }else{
               nColumns = 2;
            }

        }else{
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                nColumns = 2;
            }else{
                nColumns = 1;
            }
        }
        return new GridLayoutManager(this,nColumns);
    }


}
