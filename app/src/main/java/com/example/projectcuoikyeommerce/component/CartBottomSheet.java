package com.example.projectcuoikyeommerce.component;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.CheckoutActivity;
import com.example.projectcuoikyeommerce.adapter.CartAdapter;
import com.example.projectcuoikyeommerce.model.Cart;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class CartBottomSheet extends BottomSheetDialogFragment {
    private BottomSheetDialog bottomSheetDialog;
    private View view;
    private ImageButton btnCancel;
    private RecyclerView recyclerviewCart;
    private CartAdapter cartAdapter;
    private List<Cart> cartList = new ArrayList<>();
    private ImageButton btnBuy;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.cart_bottom_sheet, null);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(false);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        initUi();
        handleAction();
        initCart();
        return bottomSheetDialog;
    }

    private void initCart() {
        cartList.add(null);
        cartList.add(null);
        cartList.add(null);
        cartList.add(null);
        cartList.add(null);
        cartList.add(null);

        cartAdapter = new CartAdapter(cartList,null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewCart.setLayoutManager(linearLayoutManager);
        recyclerviewCart.setAdapter(cartAdapter);
    }

    private void handleAction() {
        btnCancel.setOnClickListener(v -> bottomSheetDialog.dismiss());
        btnBuy.setOnClickListener(b->{
            Intent intent = new Intent(getContext(), CheckoutActivity.class);
            startActivity(intent);
        });
    }

    private void initUi() {
        btnCancel = view.findViewById(R.id.btnCloseCart);
        recyclerviewCart = view.findViewById(R.id.recyclerviewCart);
        btnBuy = view.findViewById(R.id.btnBuy);
    }
}
