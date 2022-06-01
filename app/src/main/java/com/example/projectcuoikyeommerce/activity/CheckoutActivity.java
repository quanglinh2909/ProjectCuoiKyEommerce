package com.example.projectcuoikyeommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.CartAdapter;
import com.example.projectcuoikyeommerce.constant.KeyIntent;
import com.example.projectcuoikyeommerce.dto.ProductCartDto;
import com.example.projectcuoikyeommerce.event.CartEvent;
import com.example.projectcuoikyeommerce.model.Cart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity implements CartEvent {
    private RecyclerView recyclerviewCart;
    private CartAdapter cartAdapter;
    private List<ProductCartDto> cartList = new ArrayList<>();
    private ImageButton backButton;
    private Button btnCheckout;
    private String TAG = "AAA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getData();
        initUi();
        initCart();
        handleAction();
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String data = bundle.getString(KeyIntent.KEY_CHECK_OUT,"");
            if(!data.isEmpty() ){
                Gson goGson = new Gson();
                cartList = goGson.fromJson(data, new TypeToken<List<ProductCartDto>>(){}.getType());
            }

        }
    }

    private void initCart() {
        cartAdapter = new CartAdapter(cartList,null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewCart.setLayoutManager(linearLayoutManager);
        recyclerviewCart.setAdapter(cartAdapter);
    }
    private void handleAction() {
        btnCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(CheckoutActivity.this,AddAddressActivity.class);
            startActivity(intent);
        });
        backButton.setOnClickListener(v -> finish());
    }
    private void initUi() {
        recyclerviewCart = findViewById(R.id.recyclerviewCheckout);
        btnCheckout = findViewById(R.id.btnCheckout);
        backButton = findViewById(R.id.back_button);
    }

    @Override
    public void clickIncrease(int position) {

    }

    @Override
    public void clickReduced(int position) {

    }

    @Override
    public void inputEditTex(int position, int value) {

    }

    @Override
    public void selectItem(int position, boolean isChecked) {

    }
}