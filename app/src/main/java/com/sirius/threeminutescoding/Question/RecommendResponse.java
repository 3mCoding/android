package com.sirius.threeminutescoding.Question;

import com.google.gson.annotations.SerializedName;

public class RecommendResponse {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
