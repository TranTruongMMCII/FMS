package com.example.feedback_management.service;

import com.example.feedback_management.model.Question;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionService {
    @GET("api/v1/questions")
    Call<ArrayList<Question>> getQuestions();

//    @GET("api/v1/questions/{id}")
//    Call<ArrayList<Question>> getQuestionByTopicId();
}
