package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.model.Banner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BannerController {
    @GET("banner/get-all")
    Call<List<Banner>> getListBanner();
}
