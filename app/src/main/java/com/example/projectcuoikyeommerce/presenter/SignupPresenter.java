package com.example.projectcuoikyeommerce.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.projectcuoikyeommerce.activity.LoginActivity;
import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupPresenter {
    private Context mContext;

    public SignupPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void createUser(String name, String pass, String email){
        if(name.trim().isEmpty() || pass.trim().isEmpty()|| email.trim().isEmpty()){
            Toast.makeText(mContext, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
            if(validate(email)){
                User user = new User(name,email,pass, DataLocalManager.getInstance().getToken());
                ApiUtils.user().createUser(user).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.message().equals("Created")){
                            Intent intent = new Intent(mContext.getApplicationContext(), LoginActivity.class);
                            mContext.startActivity(intent);
                        }else{
                            Toast.makeText(mContext, "Email Hoặc tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }else{
                Toast.makeText(mContext, "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
            }

        }

    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
