package com.example.projectcuoikyeommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.component.CheckoutDialog;
import com.example.projectcuoikyeommerce.constant.Main;

import android.os.Bundle;
import android.widget.Button;

public class AddAddressActivity extends AppCompatActivity {
    private Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initUi();
        handleAction();
    }
    private void handleAction() {
        btnCheckout.setOnClickListener(v -> {
            CheckoutDialog cdd=new CheckoutDialog(AddAddressActivity.this);
            cdd.show();
        });
    }
    private void initUi() {
        btnCheckout = findViewById(R.id.btnCheckout);
    }
}