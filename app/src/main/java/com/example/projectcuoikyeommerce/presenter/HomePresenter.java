package com.example.projectcuoikyeommerce.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.dto.ProductDto;
import com.example.projectcuoikyeommerce.model.Notifycation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HomePresenter {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<ProductDto> search(String key) {
        List<ProductDto> list = new ArrayList<>();
        try {
            CompletableFuture<List<ProductDto>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.product().search(key).execute().body();
                } catch (IOException e) {
                    return list;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  list;
        }
    }
}
