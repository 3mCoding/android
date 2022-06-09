package com.sirius.threeminutescoding.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentList implements Serializable {
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
