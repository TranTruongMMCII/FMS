package com.example.fms_android.service;



import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FeedbackService {
    @GET("api/v1/Feedback")
    Call<ArrayList<com.example.fms_android.model.Feedback>> getFeedbacks();

    @GET("api/v1/last_Feedback")
    Call<com.example.fms_android.model.Feedback> getLastFeedback();

    @GET("api/v1/Feedback/{id}")
    Call<com.example.fms_android.model.Feedback> getFeedback(@Path("id") Long id);

    @DELETE("api/v1/Feedback/{id}")
    Call<ResponseBody> deleteFeedback(@Path("id") Long id);

    @DELETE("api/v1/FeedbackQuestions/{id}/{questionid}")
    Call<ResponseBody> deleteFeedbackQuestion(@Path("id") Long id, @Path("questionid") Long questionid);

    @POST("api/v1/FeedbackQuestions/{id}/{questionid}")
    Call<ResponseBody> insertFeedbackQuestion(@Path("id") Long id, @Path("questionid") Long questionid);

    @PUT("api/v1/Feedback/{id}/{typeid}/{title}")
    Call<ResponseBody> updateFeedbackQuestion(@Path("id") Long id, @Path("typeid") Long typeid, @Path("title") String title);

    @POST("api/v1/Feedback/{title}/{userName}/{typeID}")
    Call<ResponseBody> insertFeedback(@Path("title") String title, @Path("userName") String userName, @Path("typeID") Long typeid);

}
