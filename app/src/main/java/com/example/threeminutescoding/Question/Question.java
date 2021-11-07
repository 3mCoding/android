package com.example.threeminutescoding.Question;

import com.google.gson.annotations.SerializedName;

public class Question {
    @SerializedName("no")
    private int no;
    @SerializedName("answer_num")
    private int answerNum;
    @SerializedName("question")
    private String question;
    @SerializedName("print")
    private String print;
    @SerializedName("code")
    private String code;

    public int getNo() {
        return no;
    }

    public String getQuestion() {
        return question;
    }

    public String getPrint() {
        return print;
    }

    public String getCode() {
        return code;
    }

    public int getAnswerNum() {  return answerNum;   }

}
