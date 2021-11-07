package com.example.threeminutescoding.Question;

import com.google.gson.annotations.SerializedName;

public class answerData {
    @SerializedName("id")
    private int id;
    @SerializedName("num_answers")
    private int num;
    @SerializedName("answers")
    private String answers;

    public answerData(int id, int num, String answers){
        this.id = id;
        this.num = num;
        this.answers = answers;
    }
}
