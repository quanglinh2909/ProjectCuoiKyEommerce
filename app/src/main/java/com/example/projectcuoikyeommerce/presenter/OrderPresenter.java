package com.example.projectcuoikyeommerce.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.dto.ArrayListOrder;
import com.example.projectcuoikyeommerce.dto.CartDto;
import com.example.projectcuoikyeommerce.model.Oder;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class OrderPresenter {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Oder order(ArrayListOrder order) {
        try {
            CompletableFuture<Oder> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.order().insert(order).execute().body();
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
