package com.example.feedback_management.service;

import com.example.feedback_management.model.TypeFeedback;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TypeFeedbackService {
    @GET("api/v1/TypeFeedback")
    Call<ArrayList<TypeFeedback>> getTypeFeedbacks();
}
