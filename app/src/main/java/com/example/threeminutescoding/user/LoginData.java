package com.sirius.threeminutescoding.user;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("email")
    String userEmail;

    @SerializedName("password")
    String userPw;

    @SerializedName("step")
    int userStep;
    public LoginData(String userEmail, String userPw){
        this.userEmail = userEmail;
        this.userPw = userPw;
    }
}
