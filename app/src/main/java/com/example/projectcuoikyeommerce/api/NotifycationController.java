package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.dto.ArrayListOrder;
import com.example.projectcuoikyeommerce.model.Notifycation;
import com.example.projectcuoikyeommerce.model.Oder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotifycationController {
    @GET("notification/get-by-id/{id}")
    Call<List<Notifycation>> getByIdUser(@Path("id") int idUser);

    @POST("notification/update/{id}")
    Call<Notifycation> setStatus(@Path("id") int id);
}
