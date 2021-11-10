package com.sirius.threeminutescoding.Question;

import com.google.gson.annotations.SerializedName;

public class QuestionList {
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
