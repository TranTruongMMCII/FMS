package com.example.feedback_management.service;

import com.example.feedback_management.model.Topic;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TopicService {
    @GET("api/v1/topic")
    Call<ArrayList<Topic>> getTopics();
}
