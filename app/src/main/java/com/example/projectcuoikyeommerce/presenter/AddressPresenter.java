package com.example.projectcuoikyeommerce.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.dto.ProductCartDto;
import com.example.projectcuoikyeommerce.model.Province;
import com.example.projectcuoikyeommerce.model.UserInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AddressPresenter {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Province> getProvince() {
        List<Province> provinces = new ArrayList<>();
        try {
            CompletableFuture<List<Province>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.address().listProvince().execute().body();
                } catch (IOException e) {
                    return provinces;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  provinces;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Province> getDistricts(String idCode) {
        List<Province> provinces = new ArrayList<>();
        try {
            CompletableFuture<List<Province>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.address().listDistrict(idCode).execute().body();
                } catch (IOException e) {
                    return provinces;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  provinces;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Province> getWards(String idProvince,String idDistrict) {
        List<Province> provinces = new ArrayList<>();
        try {
            CompletableFuture<List<Province>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.address().listWard(idProvince,idDistrict).execute().body();
                } catch (IOException e) {
                    return provinces;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  provinces;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public UserInfo insertAddress(UserInfo userInfo) {
        try {
            CompletableFuture<UserInfo> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.address().insert(userInfo).execute().body();
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
