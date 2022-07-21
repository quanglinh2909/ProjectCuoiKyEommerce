package com.example.projectcuoikyeommerce.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.CartAdapter;
import com.example.projectcuoikyeommerce.component.CheckoutDialog;
import com.example.projectcuoikyeommerce.component.FormatPrice;
import com.example.projectcuoikyeommerce.constant.KeyIntent;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.dto.ArrayListOrder;
import com.example.projectcuoikyeommerce.dto.ProductCartDto;
import com.example.projectcuoikyeommerce.event.CartEvent;
import com.example.projectcuoikyeommerce.model.Cart;
import com.example.projectcuoikyeommerce.model.Oder;
import com.example.projectcuoikyeommerce.model.Province;
import com.example.projectcuoikyeommerce.model.User;
import com.example.projectcuoikyeommerce.model.UserInfo;
import com.example.projectcuoikyeommerce.presenter.CheckOutPresenter;
import com.example.projectcuoikyeommerce.presenter.OrderPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity implements CartEvent {
    private RecyclerView recyclerviewCart;
    private CartAdapter cartAdapter;
    private List<ProductCartDto> cartList = new ArrayList<>();
    private ImageButton backButton;
    private Button btnCheckout;
    private String TAG = "AAA";
    private RelativeLayout boxProgressBar;
    private TextView txtSubTotal,txtLocation;
    private CheckOutPresenter checkOutPresenter=  new CheckOutPresenter();
    private UserInfo userInfo;
    private ImageButton back_button;
    private OrderPresenter orderPresenter = new OrderPresenter();

    private ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            Bundle bundle = intent.getExtras();
                            if (bundle != null) {
                                UserInfo userInfo1 = (UserInfo) bundle.getSerializable("userInfo");
                                txtLocation.setTextColor(Color.BLACK);
                                txtLocation.setText(userInfo1.getFullName()+" | (+84) "+ userInfo1.getTelephone()
                                        +", "+userInfo1.getAddress()+", "+ userInfo1.getWard()+", "+ userInfo1.getDistrict()+", "
                                        +userInfo1.getProvince());
                                userInfo= userInfo1;
                            }
                            boxProgressBar.setVisibility(View.GONE);

                        }
                    });
    @RequiresApi(api = Build.VERSION_CODES.N)
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initCart() {
        cartAdapter = new CartAdapter(cartList,null,1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewCart.setLayoutManager(linearLayoutManager);
        recyclerviewCart.setAdapter(cartAdapter);
        txtSubTotal.setText(FormatPrice.formatPrice(checkOutPresenter.getSubTotalPrice(cartList)));

         userInfo = checkOutPresenter.addressDefault();
        if(userInfo != null){
            txtLocation.setTextColor(Color.BLACK);
            txtLocation.setText(userInfo.getFullName()+" | (+84) "+ userInfo.getTelephone()
            +", "+userInfo.getAddress()+", "+ userInfo.getWard()+", "+ userInfo.getDistrict()+", "
            +userInfo.getProvince());
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleAction() {
        btnCheckout.setOnClickListener(v -> {
            boxProgressBar.setVisibility(View.VISIBLE);
            CheckoutDialog checkoutDialog = new CheckoutDialog(this);
            User user = DataLocalManager.getInstance().getUser();
            if(userInfo != null){
                ArrayListOrder arrayListOrder  = new ArrayListOrder(cartList,user.getId()+"",userInfo.getId()+"",checkOutPresenter.getSubTotalPrice(cartList));
               Oder oder= orderPresenter.order(arrayListOrder);
               if(oder != null) checkoutDialog.show();
               else Toast.makeText(this, "Lỗi đặt hàng", Toast.LENGTH_SHORT).show();
            }
            boxProgressBar.setVisibility(View.GONE);

        });
        backButton.setOnClickListener(v -> finish());
        txtLocation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                boxProgressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(CheckoutActivity.this, GetAddressActivity.class);
                if(userInfo != null){
                    intent.putExtra("user",userInfo);
                }
                activityResultLauncher.launch(intent);
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initUi() {
        recyclerviewCart = findViewById(R.id.recyclerviewCheckout);
        btnCheckout = findViewById(R.id.btnCheckout);
        backButton = findViewById(R.id.back_button);
        boxProgressBar = findViewById(R.id.boxProgressBar);
        txtSubTotal = findViewById(R.id.txtSubTotal);
        txtLocation = findViewById(R.id.txtLocation);
        back_button = findViewById(R.id.back_button);
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