package com.example.projectcuoikyeommerce.presenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.projectcuoikyeommerce.activity.MainActivity;
import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    public Activity activity;


    public LoginPresenter(Activity activity) {
        this.activity = activity;
    }
    public void login(String name,String pass){
        if(name.trim().isEmpty() || pass.trim().isEmpty()){
            Toast.makeText(activity, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else{
            User user= new User(name,name,pass);
            ApiUtils.user().login(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.message().equals("Created")){
                        User user1 = response.body();
//                        Log.d("AAA", user1.toString());
                        DataLocalManager.getInstance().setUser(user1);
                        Intent intent = new Intent(activity.getApplicationContext(), MainActivity.class);
                        activity.startActivity(intent);
                        activity.finishAffinity();

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
}
