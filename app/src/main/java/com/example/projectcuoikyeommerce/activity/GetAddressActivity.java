package com.example.projectcuoikyeommerce.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.GetAddressAdapter;
import com.example.projectcuoikyeommerce.event.EventGetAddress;
import com.example.projectcuoikyeommerce.model.UserInfo;
import com.example.projectcuoikyeommerce.presenter.CheckOutPresenter;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class GetAddressActivity extends AppCompatActivity implements EventGetAddress {
    private RecyclerView recyclerView;
    private GetAddressAdapter addressAdapter;
    private List<UserInfo> userInfoList = new ArrayList<>();
    private CheckOutPresenter checkOutPresenter =new CheckOutPresenter();
    private FrameLayout containerCheckout;
    private UserInfo mUserInfo;
    private RelativeLayout boxProgressBar;
    private TextView btnAddAddress;
    private ImageButton back_button;
    private ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            Bundle bundle = intent.getExtras();
                            if (bundle != null) {
                                userInfoList = checkOutPresenter.getListUserInfo();
                                addressAdapter = new GetAddressAdapter(userInfoList,this,mUserInfo);
                                recyclerView.setAdapter(addressAdapter);
                            }
                            boxProgressBar.setVisibility(View.GONE);

                        }
                    });
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_address);
        Intent intent = getIntent();
        if(intent.hasExtra("user")) mUserInfo = (UserInfo) intent.getSerializableExtra("user");
        initUI();
        initData();
        handeleAction();
    }

    private void handeleAction() {
        btnAddAddress.setOnClickListener(v -> {
            boxProgressBar.setVisibility(View.VISIBLE);
            Intent intent = new Intent(GetAddressActivity.this, AddAddressActivity.class);
            activityResultLauncher.launch(intent);
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetAddressActivity.this,CheckoutActivity.class);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        userInfoList = checkOutPresenter.getListUserInfo();
        addressAdapter = new GetAddressAdapter(userInfoList,this,mUserInfo);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addressAdapter);
        boxProgressBar.setVisibility(View.GONE);
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerViewAddress);
        containerCheckout = findViewById(R.id.containerCheckout);
        boxProgressBar = findViewById(R.id.boxProgressBar);
        btnAddAddress = findViewById(R.id.btnAddAddress);
        back_button = findViewById(R.id.back_button);

    }

    @Override
    public void clickSelectAddress(int position, UserInfo userInfo) {
        Intent intent = new Intent(GetAddressActivity.this,CheckoutActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userInfo",userInfo);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();

    }

    @Override
    public void clickEditAddress(int position, UserInfo userInfo) {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(GetAddressActivity.this,CheckoutActivity.class);
        setResult(RESULT_OK,intent);
        finish();
    }
}