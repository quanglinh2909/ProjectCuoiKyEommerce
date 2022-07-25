package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserController {
    @POST("user/create")
    Call<User> createUser(@Body User user);

    @POST("user/login")
    Call<User> login(@Body User user);

    @POST("user/logout/{id}")
    Call<User> logout(@Path("id") String  idUser);

    @GET("user/get-user-by-id/{id}")
    Call<User> getUserByID(@Path("id") int  idUser);

    @GET("user/get-all-user")
    Call<List<User>> getAllUser();
}
