package com.example.fms_android.service;

import com.example.fms_android.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface UserService {
    @GET("api/v1/users")
    Call<ArrayList<User>> getUsers();

    @GET("api/v1/users/{id}")
    Call<User> getUserById(@Path("id") long id);

    @POST("api/v1/users")
    Call<User> postUser(@Body User user);

    @PUT("api/v1/users/{id}")
    Call<Void> updateUser(@Path("id") long id, @Body User user);

    @DELETE("api/v1/users/{id}")
    Call<Void> delete(@Path("id") long id);
}
