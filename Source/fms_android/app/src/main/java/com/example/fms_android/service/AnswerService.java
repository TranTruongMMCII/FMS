package com.example.fms_android.service;

import com.example.fms_android.model.Answer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AnswerService {
    @GET("api/answers")
    Call<ArrayList<Answer>> getAnswers();

    @GET("api/answers/{classID}/{moduleID}/{traineeID}/{questionID}")
    Call<Answer> getAnswerById(@Path("classID") long classID, @Path("moduleID") long moduleID,
                               @Path("traineeID") String traineeID, @Path("questionID") long questionID);

    @POST("api/answers")
    Call<Answer> postAnswer(@Body Answer answer);

    @PUT("api/answers/{classID}/{moduleID}/{traineeID}/{questionID}")
    Call<Void> updateAnswer(@Path("classID") long classID, @Path("moduleID") long moduleID,
                            @Path("traineeID") String traineeID, @Path("questionID") long questionID,
                            @Body Answer answer);

    @DELETE("api/answers/{classID}/{moduleID}/{traineeID}/{questionID}")
    Call<Void> deleteAnswer(@Path("classID") long classID, @Path("moduleID") long moduleID,
                            @Path("traineeID") String traineeID, @Path("questionID") long questionID);

}
