package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.dto.ArrayListOrder;
import com.example.projectcuoikyeommerce.dto.CartDto;
import com.example.projectcuoikyeommerce.model.Cart;
import com.example.projectcuoikyeommerce.model.Oder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OderController {
    @POST("order/insert")
    Call<Oder> insert(@Body ArrayListOrder arrayListOrder);
}
