package com.android.pena.david.bakingapp.Utils;

import android.widget.SimpleCursorAdapter;

import com.android.pena.david.bakingapp.Model.Ingredient;
import com.android.pena.david.bakingapp.Model.Recipe;
import com.android.pena.david.bakingapp.Model.Step;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by david on 22/05/17.
 */

public class AutoValueTypeAdapterFactory implements TypeAdapterFactory {

    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> rawType = type.getRawType();


        if (rawType.equals(Ingredient.class)) {
            return (TypeAdapter<T>) Ingredient.typeAdapter(gson);
        }
        if (rawType.equals(Recipe.class)) {
            Type t = type.getType();


            //((ParameterizedType) t).getActualTypeArguments();
            TypeToken<? extends Recipe<Ingredient,Step>> newTypeToken;
            newTypeToken = (TypeToken<? extends Recipe<Ingredient, Step>>) TypeToken.get( type.getType());
            TypeAdapter<Recipe<Ingredient,Step>> typeAdapter = Recipe.typeAdapter(gson,newTypeToken);
            return (TypeAdapter<T>) typeAdapter;
        }
        if (rawType.equals(Step.class)) {
            return (TypeAdapter<T>) Step.typeAdapter(gson);
        }
        return null;
    }


    private <Ingredient,Step> TypeAdapter<Recipe<Ingredient,Step>> newMultisetAdapter(
    ){
    return null;
    }

    }



