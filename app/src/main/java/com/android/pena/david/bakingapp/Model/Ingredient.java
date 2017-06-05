package com.android.pena.david.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by david on 22/05/17.
 */

public class Ingredient implements Parcelable{
    private String measure;
    private String ingredient;
    private float quantity;

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public float getQuantity() {
        return quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(measure);
        dest.writeString(ingredient);
        dest.writeFloat(quantity);


    }

    // Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    // "De-parcel object
    public Ingredient(Parcel in) {
        this.quantity = in.readFloat();
        this.ingredient = in.readString();
        this.measure = in.readString();
    }
}