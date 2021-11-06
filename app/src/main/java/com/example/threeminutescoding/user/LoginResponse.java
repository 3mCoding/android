package com.example.threeminutescoding.user;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("step")
    private int step;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStep() {
        return step;
    }
}
