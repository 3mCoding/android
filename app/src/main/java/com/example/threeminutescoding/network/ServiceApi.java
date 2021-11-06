package com.example.threeminutescoding.network;

import com.example.threeminutescoding.Question.Question;
import com.example.threeminutescoding.user.JoinData;
import com.example.threeminutescoding.user.JoinResponse;
import com.example.threeminutescoding.user.LoginData;
import com.example.threeminutescoding.user.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {
    // 회원가입
    @POST("/user/join")
    public Call<JoinResponse> userJoin(@Body JoinData data);
    // 로그인
    @POST("/user/login")
    public Call<LoginResponse> userLogin(@Body LoginData data);
    //문제
    @GET("/question")
    public Call<List<Question>> getData(@Query("id") int id);
}
