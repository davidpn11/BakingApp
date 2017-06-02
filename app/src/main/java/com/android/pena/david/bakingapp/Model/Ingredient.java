package com.android.pena.david.bakingapp.Model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by david on 22/05/17.
 */
@AutoValue
public abstract class Ingredient {
    @SerializedName("measure")
    abstract String measure();
    @SerializedName("ingredient")
    abstract String ingredient();
    @SerializedName("quantity")
    abstract float quantity();

    public static Builder builder() {
        return new AutoValue_Ingredient.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder measure(String value);
        public abstract Builder ingredient(String value);
        public abstract Builder quantity(float value);
        public abstract Ingredient build();
    }

    public static TypeAdapter<Ingredient> typeAdapter(Gson gson) {
        return new AutoValue_Ingredient.GsonTypeAdapter(gson);

    }


}
