package com.example.fms_android.service;

import com.example.fms_android.model.Assignment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AssignmentService {
    @GET("api/assignment/getAll")
    Call<ArrayList<Assignment>> getAssignments();

    @GET("api/v1/assignment/getAssignmentById/{classId}/{moduleId}/{trainerId}")
    Call<Assignment> getAssignmentById(@Path("classId") long classId, @Path("moduleId") long moduleId, @Path("trainerId") String trainerId);

    @POST("api/v1/assignment/addAssignment")
    Call<Assignment> postAssignment(@Body Assignment assignment);

    @PUT("api/v1/assignment/updateAssignment/{classId}/{moduleId}/{trainerId}")
    Call<Void> updateAssignment(@Path("classId") long classId, @Path("moduleId") long moduleId, @Path("trainerId") String trainerId, @Body Assignment assignment);

    @DELETE("api/v1/assignments/deleteAssignment/{classId}/{moduleId}/{trainerId}")
    Call<Void> delete(@Path("classId") long classId, @Path("moduleId") long moduleId, @Path("trainerId") String trainerId);
}
