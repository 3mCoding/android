package com.example.threeminutescoding;

public class User {
    String number;
    String name;
    int problem;

    public User(String number, String name, int problem) {
        this.number = number;
        this.name = name;
        this.problem = problem;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProblem() {
        return problem;
    }

    public void setProblem(int problem) {
        this.problem = problem;
    }
}
