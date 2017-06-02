package com.android.pena.david.bakingapp.Model;

import java.util.List;

/**
 * Created by david on 22/05/17.
 */



public class Recipe{
    private int id;
    private String name;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private String servings;
    private String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public List<Step> getSteps() {
        return steps;
    }
}