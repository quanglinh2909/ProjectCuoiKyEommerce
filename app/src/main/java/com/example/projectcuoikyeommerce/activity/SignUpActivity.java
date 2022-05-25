package com.example.projectcuoikyeommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;

import com.example.projectcuoikyeommerce.presenter.SignupPresenter;

public class SignUpActivity extends AppCompatActivity {
    private TextView textLogin;
    private EditText edtName,editPass,edtEmail;
    private Button btnSignup;
    private SignupPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initUI();
        handleAction();


    }

    private void initUI() {
        textLogin = findViewById(R.id.textLogin);
        edtName = findViewById(R.id.name);
        editPass = findViewById(R.id.password);
        edtEmail = findViewById(R.id.username);
        btnSignup = findViewById(R.id.signup);
        loginPresenter = new SignupPresenter(SignUpActivity.this);
    }

    private void handleAction() {
        textLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
            startActivity(intent);
        });
        btnSignup.setOnClickListener(v -> {
            String name=edtName.getText().toString();
            String pass=editPass.getText().toString();
            String email=edtEmail.getText().toString();
            loginPresenter.createUser(name,pass,email);
        });
    }

}