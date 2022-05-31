package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.model.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ImageController {
    @GET("image/get-by-id-product/{id}")
    Call<List<Image>> getListImage(@Path("id") String id);


}
