package com.sirius.threeminutescoding.network;

import com.sirius.threeminutescoding.Question.AnswerResponse;
import com.sirius.threeminutescoding.Question.Description;
import com.sirius.threeminutescoding.Question.Question;
import com.sirius.threeminutescoding.Question.QuestionList;
import com.sirius.threeminutescoding.Question.RecommendResponse;
import com.sirius.threeminutescoding.Question.answerData;
import com.sirius.threeminutescoding.user.JoinData;
import com.sirius.threeminutescoding.user.JoinResponse;
import com.sirius.threeminutescoding.user.LoginData;
import com.sirius.threeminutescoding.user.LoginResponse;
import com.sirius.threeminutescoding.user.StepResponse;
import com.sirius.threeminutescoding.user.StudentList;
import com.sirius.threeminutescoding.user.UpdateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    // 회원가입
    @POST("/user/join")
    public Call<JoinResponse> userJoin(@Body JoinData data);
    // 로그인
    @POST("/user/login")
    public Call<LoginResponse> userLogin(@Body LoginData data);
    // 회원 정보 수정
    @POST("/user/update")
    public Call<UpdateResponse> userUpdate(@Query("email") String email, @Query("student_num") int student_num, @Query("name") String name);
    //문제 화면
    @GET("/question/type/{type}")
    public Call<List<Question>> questionData(@Path ("type") String type, @Query("no") int no);
    //문제 - 정답 확인
    @POST("/question/answer")
    public Call<AnswerResponse> questionAnswer(@Body answerData data);
    //문제 - step update
    @POST("/user/pass")
    public Call<StepResponse> stepData(@Query("email") String email, @Query("step") int step);
    //문제 - 설명
    @GET("/question/description")
    public Call<List<Description>> descriptionData(@Query("id") int id);
    //문제 추천
    @POST("/question/recommend")
    public Call<RecommendResponse> recData(@Query("content") String content, @Query("student_num") int student_num);
    //문제 리스트
    @GET("/question/list")
    public Call<List<QuestionList>> questionListData();
    //학생 리스트
    @GET("/user/list")
    public Call<List<StudentList>> studentListData();
}
