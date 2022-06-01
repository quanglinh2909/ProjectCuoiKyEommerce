package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.TagParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrademarkController {
    @GET("local-branch/get-all")
    Call<List<Branch>> listTrademark();
}
