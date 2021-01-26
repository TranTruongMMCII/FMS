package com.example.fms_android.service;

import com.example.fms_android.model.QuestionOfTruong;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionService {
    @GET("api/v1/questions/getAll")
    Call<ArrayList<QuestionOfTruong>> getQuestions();

//    @GET("api/v1/questions/{id}")
//    Call<ArrayList<Question>> getQuestionByTopicId();
}
