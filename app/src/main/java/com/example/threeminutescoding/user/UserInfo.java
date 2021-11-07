package com.example.threeminutescoding.user;


import android.util.Log;

public class UserInfo {
    private static int step;
    public static void setUserInfo(int _step){
        step = _step;
    }
    public static int getStep(){
        Log.d("myapp", "getStep : " +step);
        return step;
    }
}
