package com.example.threeminutescoding.Question;

import com.google.gson.annotations.SerializedName;

public class AnswerResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("pass")
    private String pass;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getPass() {
        return pass;
    }
}
