package com.example.threeminutescoding.user;

import com.google.gson.annotations.SerializedName;

public class StepResponse {
    @SerializedName("step")
    private int step;

    public int getStep() {
        return step;
    }
}
