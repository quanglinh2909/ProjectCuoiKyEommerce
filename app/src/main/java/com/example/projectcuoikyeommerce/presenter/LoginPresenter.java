package com.example.projectcuoikyeommerce.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.activity.LoginActivity;
import com.example.projectcuoikyeommerce.activity.MainActivity;
import com.example.projectcuoikyeommerce.activity.admin.DashBoardActivity;
import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.dto.ArrayListOrder;
import com.example.projectcuoikyeommerce.dto.OrderAdminDto;
import com.example.projectcuoikyeommerce.model.Oder;
import com.example.projectcuoikyeommerce.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    public Activity activity;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public User createUser(String name, String pass, String email) {
        User user = new User(name,email,pass, DataLocalManager.getInstance().getToken());
        try {
            CompletableFuture<User> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.user().loginGoogle(user).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  null;
        }
    }

    public LoginPresenter(Activity activity) {
        this.activity = activity;
    }
    public void login(String name,String pass){
        if(name.trim().isEmpty() || pass.trim().isEmpty()){
            Toast.makeText(activity, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else{
            User user= new User(name,name,pass,DataLocalManager.getInstance().getToken());
            ApiUtils.user().login(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.message().equals("Created")){
                        User user1 = response.body();
//                        Log.d("AAA", user1.toString());
                        DataLocalManager.getInstance().setUser(user1);
                        if(user1.getRole() == 0){
                            Intent intent = new Intent(activity.getApplicationContext(),MainActivity.class);
                            activity.startActivity(intent);
                            activity.finishAffinity();

                        }
                        if(user1.getRole() == 1){
                            Intent intent = new Intent(activity.getApplicationContext(), DashBoardActivity.class);
                            activity.startActivity(intent);
                            activity.finishAffinity();
                        }


                    }else{
                        Toast.makeText(activity, "Tên đăng nhập hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public User logOut() {

        try {
            CompletableFuture<User> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.user().logout(DataLocalManager.getInstance().getUser().getId()+"").execute().body();
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
