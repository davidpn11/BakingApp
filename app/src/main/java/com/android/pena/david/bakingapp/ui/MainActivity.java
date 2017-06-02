package com.android.pena.david.bakingapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.pena.david.bakingapp.Model.Recipe;
import com.android.pena.david.bakingapp.R;
import com.android.pena.david.bakingapp.Utils.ApiClient;
import com.android.pena.david.bakingapp.Utils.ApiInterface;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRecipesfromAPI();
    }

    private void getRecipesfromAPI(){
        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Recipe>> apiCall = apiService.getRecipes();

        apiCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipes = response.body();
            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
