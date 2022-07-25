package com.example.projectcuoikyeommerce.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.dto.ProductCartDto;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.User;
import com.example.projectcuoikyeommerce.model.UserInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CheckOutPresenter {
    public double getSubTotalPrice(List<ProductCartDto> productCartDtoList){
        double sum = 0;
        for (ProductCartDto p : productCartDtoList){
            sum += (p.getProductEntityPrice()*p.getCartEntityQuantity());
        }

        return sum;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<UserInfo> getListUserInfo() {
        List<UserInfo> userInfos = new ArrayList<>();
        User user = DataLocalManager.getInstance().getUser();
        try {
            CompletableFuture<List<UserInfo>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.address().getListAddress(user.getId()).execute().body();
                } catch (IOException e) {
                    return userInfos;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  userInfos;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public UserInfo addressDefault(){
        List<UserInfo> userInfos = getListUserInfo();
        if(userInfos == null) return null;
        for(UserInfo u: userInfos){
            if(u.getDefault() == 1) return  u;
        }
        return null;
    }
}
