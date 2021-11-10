package com.sirius.threeminutescoding.user;

import com.google.gson.annotations.SerializedName;

public class StudentList {
    @SerializedName("student_num")
    private String stuNum;

    @SerializedName("name")
    private String userName;

    @SerializedName("step")
    private int userStep;

    public String getStuNum() {
        return stuNum;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserStep() {
        return userStep;
    }
}
