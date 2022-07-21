package com.example.projectcuoikyeommerce.component;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.MainActivity;

import androidx.annotation.NonNull;

public class CheckoutDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private Button btnCheckout, btnBackToHome;
    public CheckoutDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_diaglog);
        initUI();
    }
    public void initUI(){
        btnCheckout = findViewById(R.id.btn_submit);
        btnBackToHome = findViewById(R.id.btn_back);
        btnCheckout.setOnClickListener(this);
        btnBackToHome.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:
                break;
            case R.id.btn_back:
                Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
