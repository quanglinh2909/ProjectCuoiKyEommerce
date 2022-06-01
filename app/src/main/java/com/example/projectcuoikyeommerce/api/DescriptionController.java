package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.model.Description;
import com.example.projectcuoikyeommerce.model.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DescriptionController {
    @GET("description/get-by-id-product/{id}")
    Call<List<Description>> getListDescription(@Path("id") String id);
}
