package com.android.pena.david.bakingapp.Utils;

import com.android.pena.david.bakingapp.Model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by david on 02/06/17.
 */

public interface ApiInterface {


    @GET("baking.json")
    Call<List<Recipe>> getRecipes();
}
