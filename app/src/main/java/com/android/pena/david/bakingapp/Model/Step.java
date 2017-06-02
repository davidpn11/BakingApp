package com.android.pena.david.bakingapp.Model;

/**
 * Created by david on 22/05/17.
 */

public class Step{
    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;


    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
