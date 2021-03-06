package com.sirius.threeminutescoding.user;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("step")
    private int step;

    @SerializedName("rank")
    private int rank;

    @SerializedName("student_num")
    private int student_num;

    @SerializedName("name")
    private String name;

    @SerializedName("date")
    private String date;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStep() {
        return step;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getRank() {
        return rank;
    }

    public int getStudent_num() {
        return student_num;
    }
}
