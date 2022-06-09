package com.sirius.threeminutescoding.Question;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionList implements Serializable {
    @SerializedName("no")
    private int no;
    @SerializedName("title")
    private String title;

    public String getTitle() {
        return title;
    }

    public int getNo() {
        return no;
    }
}
