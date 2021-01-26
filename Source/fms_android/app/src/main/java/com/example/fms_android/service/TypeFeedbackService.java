package com.example.fms_android.service;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TypeFeedbackService {
    @GET("api/v1/TypeFeedback")
    Call<ArrayList<com.example.fms_android.model.TypeFeedback>> getTypeFeedbacks();
}
