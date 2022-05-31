package com.example.projectcuoikyeommerce.component;

import android.app.Dialog;
import android.os.Build;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.CheckoutActivity;
import com.example.projectcuoikyeommerce.adapter.CartAdapter;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.dto.CartDto;
import com.example.projectcuoikyeommerce.dto.ProductCartDto;
import com.example.projectcuoikyeommerce.event.CartEvent;
import com.example.projectcuoikyeommerce.model.Cart;
import com.example.projectcuoikyeommerce.presenter.CartPresenter;
import com.example.projectcuoikyeommerce.presenter.CategoryPresenter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class CartBottomSheet extends BottomSheetDialogFragment implements CartEvent {
    private BottomSheetDialog bottomSheetDialog;
    private View view;
    private ImageButton btnCancel;
    private RecyclerView recyclerviewCart;
    private CartAdapter cartAdapter;
    private List<ProductCartDto> cartList = new ArrayList<>();
    private CartPresenter presenter;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initCart() {
        cartList.clear();
        cartList.addAll(presenter.getProduct(DataLocalManager.getInstance().getUser().getId()));
        ;

        cartAdapter = new CartAdapter(cartList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewCart.setLayoutManager(linearLayoutManager);
        recyclerviewCart.setAdapter(cartAdapter);
    }

    private void handleAction() {
        btnCancel.setOnClickListener(v -> bottomSheetDialog.dismiss());
        btnBuy.setOnClickListener(b -> {
            Intent intent = new Intent(getContext(), CheckoutActivity.class);
            startActivity(intent);
        });
    }

    private void initUi() {
        btnCancel = view.findViewById(R.id.btnCloseCart);
        recyclerviewCart = view.findViewById(R.id.recyclerviewCart);
        presenter = new CartPresenter();
        btnBuy = view.findViewById(R.id.btnBuy);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void clickIncrease(int position) {
        ProductCartDto cartDto = cartList.get(position);
        //Integer id, Integer idUser, String idProduct, Integer quantity, String size
        CartDto cart = new CartDto(cartDto.getCartEntityId(), cartDto.getCartEntityIdUserId()
                , cartDto.getCartEntityIdProductId(), cartDto.getCartEntityQuantity() + 1, cartDto.getCartEntitySize());
        CartDto cartDto1 = presenter.updateCard(cart);
        cartList.get(position).setCartEntityQuantity(cartDto1.getQuantity());
        cartAdapter.notifyDataSetChanged();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void clickReduced(int position) {
        ProductCartDto cartDto = cartList.get(position);
        //Integer id, Integer idUser, String idProduct, Integer quantity, String size
        CartDto cart = new CartDto(cartDto.getCartEntityId(), cartDto.getCartEntityIdUserId()
                , cartDto.getCartEntityIdProductId(), cartDto.getCartEntityQuantity() - 1, cartDto.getCartEntitySize());
        CartDto cartDto1 = presenter.updateCard(cart);
        cartList.get(position).setCartEntityQuantity(cartDto1.getQuantity());
        cartAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void inputEditTex(int position, int value) {
        ProductCartDto cartDto = cartList.get(position);

        CartDto cart = new CartDto(cartDto.getCartEntityId(), cartDto.getCartEntityIdUserId()
                , cartDto.getCartEntityIdProductId(), value, cartDto.getCartEntitySize());
        CartDto cartDto1 = presenter.updateCard(cart);
        cartList.get(position).setCartEntityQuantity(cartDto1.getQuantity());
        cartAdapter.notifyDataSetChanged();
    }
}
