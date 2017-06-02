package com.android.pena.david.bakingapp.Model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by david on 22/05/17.
 */
@AutoValue
public abstract class Step {
    @SerializedName("id")
    abstract int id();
    @SerializedName("shortDescription")
    abstract String shortDescription();
    @SerializedName("description")
    abstract String description();
    @SerializedName("videoURL")
    abstract String videoURL();
    @SerializedName("thumbnailURL")
    abstract String thumbnailURL();

    public static Builder builder() {
        return new AutoValue_Step.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(int value);
        public abstract Builder shortDescription(String value);
        public abstract Builder description(String value);
        public abstract Builder videoURL(String value);
        public abstract Builder thumbnailURL(String value);
        public abstract Step build();
    }

    public static TypeAdapter<Step> typeAdapter(Gson gson) {
        return new AutoValue_Step.GsonTypeAdapter(gson);
    }

}
