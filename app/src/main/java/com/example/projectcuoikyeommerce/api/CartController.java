package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.dto.CartDto;
import com.example.projectcuoikyeommerce.dto.ProductCartDto;
import com.example.projectcuoikyeommerce.model.Cart;
import com.example.projectcuoikyeommerce.model.Description;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartController {
    @POST("cart/insert")
    Call<Cart> insertCart(@Body CartDto cart);

    @GET("cart/get-by-id-user/{id}")
    Call<List<ProductCartDto>> getProductByIdUser(@Path("id") int id);
    @PUT("cart/update")
    Call<CartDto> updateCart(@Body CartDto cart);
}
