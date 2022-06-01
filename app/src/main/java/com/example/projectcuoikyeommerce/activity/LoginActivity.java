package com.example.projectcuoikyeommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.model.User;
import com.example.projectcuoikyeommerce.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity {
    private TextView textCreateAccount;
    private EditText username,password;
    private Button btnLogin;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        User user = DataLocalManager.getInstance().getUser();
        if(user != null){
           Intent intent = new Intent(LoginActivity.this,MainActivity.class);
           startActivity(intent);
           finishAffinity();

        }
        initUI();
        handleAction();
    }


    private void initUI() {
        textCreateAccount = findViewById(R.id.textCreateAccount);
        btnLogin = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginPresenter = new LoginPresenter(this);
    }

    private void handleAction() {
        textCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
         loginPresenter.login(username.getText().toString(),password.getText().toString());
        });
    }
}