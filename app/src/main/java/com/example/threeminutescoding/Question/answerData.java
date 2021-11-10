package com.sirius.threeminutescoding.Question;

import com.google.gson.annotations.SerializedName;

public class answerData {
    @SerializedName("id")
    private int id;
    @SerializedName("answers")
    private String answers;

    public answerData(int id, String answers){
        this.id = id;
        this.answers = answers;
    }
}
