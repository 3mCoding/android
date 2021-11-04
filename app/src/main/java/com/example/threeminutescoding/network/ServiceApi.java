package com.example.threeminutescoding.network;

import com.example.threeminutescoding.user.JoinData;
import com.example.threeminutescoding.user.JoinResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    // 회원가입
    @POST("/user/join")
    public Call<JoinResponse> userJoin(@Body JoinData data);
}
