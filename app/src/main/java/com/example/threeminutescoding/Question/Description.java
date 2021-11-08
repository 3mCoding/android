package com.example.threeminutescoding.Question;

import com.google.gson.annotations.SerializedName;

public class Description {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("desc_path")
    private String descPath;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescPath() {
        return descPath;
    }
}
