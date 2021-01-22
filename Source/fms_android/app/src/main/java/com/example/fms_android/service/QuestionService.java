package com.example.fms_android.service;


import com.example.fms_android.model.Question;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface QuestionService {

    @GET("api/v1/questions")
    Call<ArrayList<Question>> getQuestions();

    @GET("api/v1/questions")
    Call<Question> getQuestionById(@Query("id") long id);

    @POST("api/v1/questions")
    Call<Question> postQuestion(@Body Question question);

    @PUT("api/v1/questions")
    Call<Void> updateQuestion(@Query("id") long id, @Body Question question);

    @DELETE("api/v1/questions")
    Call<Void> delete(@Query("id") long id);
}
