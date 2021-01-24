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
    @GET("api/answers/getAll")
    Call<ArrayList<Answer>> getAnswers();

    @GET("api/answers/getAnswerById/{classID}/{moduleID}/{traineeID}/{questionID}")
    Call<Answer> getAnswerById(@Path("classID") long classID, @Path("moduleID") long moduleID,
                               @Path("traineeID") String traineeID, @Path("questionID") long questionID);

    @GET("/api/answers/getAnswerByClassId/{classID}")
    Call<Answer> getAnswersByClassID(@Path("classID") long classID);

    @GET("/api/answers/getAnswerByModuleId/{moduleID}")
    Call<Answer> getAnswersByModuleID(@Path("moduleID") long moduleID);

    @GET("/api/answers/getAnswerByQuestionId/{questionID}")
    Call<Answer> getAnswersByQuestionID(@Path("questionID") long questionID);

    @GET("/api/answers/getAnswerByTraineeId/{traineeID}")
    Call<Answer> getAnswersByTraineeID(@Path("traineeID") String traineeID);

    @GET("api/answers/getAnswerByValue/{value}")
    Call<Answer> getAnswerByValue(@Path("value") int value);

    @POST("api/answers/addAnswer")
    Call<Answer> postAnswer(@Body Answer answer);

    @PUT("api/answers/{classID}/{moduleID}/{traineeID}/{questionID}")
    Call<Void> updateAnswer(@Path("classID") long classID, @Path("moduleID") long moduleID,
                            @Path("traineeID") String traineeID, @Path("questionID") long questionID,
                            @Body Answer answer);

    @DELETE("api/answers/deleteAnswer/{classID}/{moduleID}/{traineeID}/{questionID}")
    Call<Void> deleteAnswer(@Path("classID") long classID, @Path("moduleID") long moduleID,
                            @Path("traineeID") String traineeID, @Path("questionID") long questionID);

}
