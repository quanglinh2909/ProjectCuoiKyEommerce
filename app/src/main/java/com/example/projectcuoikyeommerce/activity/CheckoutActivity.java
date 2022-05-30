package com.example.projectcuoikyeommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.CartAdapter;
import com.example.projectcuoikyeommerce.model.Cart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    private RecyclerView recyclerviewCart;
    private CartAdapter cartAdapter;
    private List<Cart> cartList = new ArrayList<>();
    private Button btnCheckout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        initUi();
        initCart();
        handleAction();
    }
    private void initCart() {
        cartList.add(null);
        cartList.add(null);
//        cartList.add(null);
//        cartList.add(null);
//        cartList.add(null);
//        cartList.add(null);

        cartAdapter = new CartAdapter(cartList,null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewCart.setLayoutManager(linearLayoutManager);
        recyclerviewCart.setAdapter(cartAdapter);
    }
    private void handleAction() {
        btnCheckout.setOnClickListener(v -> {
            Log.d("demo", "handleAction: de,p");
            Intent intent = new Intent(CheckoutActivity.this,AddAddressActivity.class);
            startActivity(intent);
        });
    }
    private void initUi() {
        recyclerviewCart = findViewById(R.id.recyclerviewCheckout);
        btnCheckout = findViewById(R.id.btnCheckout);
    }
}