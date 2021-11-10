package com.sirius.threeminutescoding.user;

import com.google.gson.annotations.SerializedName;

public class UpdateResponse {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
