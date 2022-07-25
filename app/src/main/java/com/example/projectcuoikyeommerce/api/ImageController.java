package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.dto.ImageUpload;
import com.example.projectcuoikyeommerce.model.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ImageController {
    @GET("image/get-by-id-product/{id}")
    Call<List<Image>> getListImage(@Path("id") String id);

    @DELETE("image/delete/{id}/{url}")
    Call<String> deleteImage(@Path("id") String id, @Path("url") String url);

    @POST("image/create")
    Call<String> create(@Body List<ImageUpload> imageList);
}
