package com.sirius.threeminutescoding.user;


import android.util.Log;

public class UserInfo {
    private static int step;
    private static String name;
    private static int rank;
    private static String joinData;
    private static String email;
    private static int student_num;

    public static void setUserInfo(int _step){
        step = _step;
    }
    public static int getStep(){
        Log.d("myapp", "getStep : " +step);
        return step;
    }

    public static void setStep(int step) {
        UserInfo.step = step;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserInfo.name = name;
    }

    public static int getRank() {
        return rank;
    }

    public static void setRank(int rank) {
        UserInfo.rank = rank;
    }

    public static String getJoinData() {
        return joinData;
    }

    public static void setJoinData(String joinData) {
        UserInfo.joinData = joinData;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserInfo.email = email;
    }

    public static int getStudent_num() {
        return student_num;
    }

    public static void setStudent_num(int student_num) {
        UserInfo.student_num = student_num;
    }
}
