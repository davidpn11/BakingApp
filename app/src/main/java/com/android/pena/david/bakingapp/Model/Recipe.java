package com.android.pena.david.bakingapp.Model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by david on 22/05/17.
 */
@AutoValue
public abstract class Recipe<Ingredient,Step> {
    @SerializedName("id")
    abstract int id();
    @SerializedName("name")
    abstract String name();
    @SerializedName("ingredients")
    abstract List<Ingredient> ingredients();
    @SerializedName("steps")
    abstract List<Step> steps();
    @SerializedName("servings")
    abstract String servings();
    @SerializedName("image")
    abstract String image();

    @SuppressWarnings("unchecked")
    public static <Ingredient,Step> TypeAdapter<Recipe<Ingredient,Step>> typeAdapter(Gson gson,
                      TypeToken<? extends Recipe<Ingredient,Step>> typeToken) {

        return new AutoValue_Recipe.GsonTypeAdapter(gson,typeToken);
    }
}
