package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserController {
    @POST("user/create")
    Call<User> createUser(@Body User user);

    @POST("user/login")
    Call<User> login(@Body User user);
}
