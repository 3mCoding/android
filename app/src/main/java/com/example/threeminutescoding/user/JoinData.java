package com.sirius.threeminutescoding.user;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("student_num")
    private String stuNum;

    @SerializedName("name")
    private String userName;

    @SerializedName("email")
    private String userEmail;

    @SerializedName("password")
    private String userPw;

    public JoinData(String stuNum, String userName, String userEmail, String userPwd){
        this.userName = userName;
        this.stuNum = stuNum;
        this.userEmail = userEmail;
        this.userPw = userPwd;
    }
}
