package com.android.pena.david.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by david on 22/05/17.
 */



public class Recipe implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(servings);
        dest.writeString(image);
        dest.writeList(ingredients);
        dest.writeList(steps);
    }

    // Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    // "De-parcel object
    public Recipe(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.servings = in.readString();
        this.image = in.readString();
        this.ingredients = new ArrayList<>();
        in.readList(this.ingredients,getClass().getClassLoader());
        this.steps = new ArrayList<>();
        in.readList(this.steps,getClass().getClassLoader());
    }
}