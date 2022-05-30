package com.example.projectcuoikyeommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.projectcuoikyeommerce.R;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

public class AddAddressActivity extends AppCompatActivity {
    private Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initUi();
    }
    private void initUi() {
        btnCheckout = findViewById(R.id.btnCheckout);
    }
}