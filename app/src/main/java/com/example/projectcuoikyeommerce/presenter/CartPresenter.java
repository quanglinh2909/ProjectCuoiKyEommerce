package com.example.projectcuoikyeommerce.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.dto.CartDto;
import com.example.projectcuoikyeommerce.dto.ProductCartDto;
import com.example.projectcuoikyeommerce.model.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CartPresenter {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<ProductCartDto> getProduct(int id) {
        List<ProductCartDto> products = new ArrayList<>();
        try {
            CompletableFuture<List<ProductCartDto>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.cart().getProductByIdUser(id).execute().body();
                } catch (IOException e) {
                    return products;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  products;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CartDto updateCard(CartDto cart) {
        try {
            CompletableFuture<CartDto> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.cart().updateCart(cart).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  null;
        }
    }
}
