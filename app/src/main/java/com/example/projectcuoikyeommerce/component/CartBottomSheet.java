package com.example.projectcuoikyeommerce.component;

import android.app.Dialog;
import android.os.Build;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.CheckoutActivity;
import com.example.projectcuoikyeommerce.adapter.CartAdapter;
import com.example.projectcuoikyeommerce.constant.KeyIntent;
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
import com.google.gson.Gson;

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
    private TextView txtTotalPrice;

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        eventRemoveItemProduct();
        initCart();
        return bottomSheetDialog;
    }

    private void eventRemoveItemProduct() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ProductCartDto cartDto = cartList.get(position);
                if(presenter.deleteCart(cartDto.getCartEntityId())!= null){
                    cartList.remove(position);
                    Toast.makeText(getContext(), "Đã xóa "+ cartDto.getProductEntityName()+" khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Lỗi!!!!", Toast.LENGTH_SHORT).show();
                }
                cartAdapter.notifyDataSetChanged();


            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerviewCart);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initCart() {
        cartList.clear();
        cartList.addAll(presenter.getProduct(DataLocalManager.getInstance().getUser().getId()));

        cartAdapter = new CartAdapter(cartList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewCart.setLayoutManager(linearLayoutManager);
        recyclerviewCart.setAdapter(cartAdapter);
    }

    private void handleAction() {
        btnCancel.setOnClickListener(v -> bottomSheetDialog.dismiss());
        btnBuy.setOnClickListener(b -> {
            List<ProductCartDto> listIntent = presenter.getListCheckout(cartList);
            if(listIntent.size() > 0){
                Intent intent = new Intent(getContext(), CheckoutActivity.class);
                Bundle bundle = new Bundle();
                Gson gson = new Gson();
                bundle.putString(KeyIntent.KEY_CHECK_OUT,gson.toJson(listIntent));
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Toast.makeText(getContext(), "Vui lòng chọn sản phẩm", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void initUi() {
        btnCancel = view.findViewById(R.id.btnCloseCart);
        recyclerviewCart = view.findViewById(R.id.recyclerviewCart);
        presenter = new CartPresenter();
        btnBuy = view.findViewById(R.id.btnBuy);
        txtTotalPrice = view.findViewById(R.id.txtTotalPrice);

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

    @Override
    public void selectItem(int position, boolean isChecked) {
        cartList.get(position).setCheck(isChecked);
        txtTotalPrice.setText(presenter.totalPrice(cartList));
    }
}
