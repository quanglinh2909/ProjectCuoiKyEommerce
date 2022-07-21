package com.example.projectcuoikyeommerce.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.component.CheckoutDialog;
import com.example.projectcuoikyeommerce.constant.Main;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.model.Province;
import com.example.projectcuoikyeommerce.model.User;
import com.example.projectcuoikyeommerce.model.UserInfo;
import com.example.projectcuoikyeommerce.presenter.AddressPresenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

public class AddAddressActivity extends AppCompatActivity {
    private Button btnCheckout;
    private RelativeLayout btnBoxSelect;
    private TextView txtAddress;
    private Province province, district, ward;
    private EditText edtName, edtPhoneNumber, edtNumberTree;
    private RadioGroup radioGroup;
    private RadioButton raNhaRieng;
    private Switch swDefault;
    private int typeAddress = 2, isDefault = 0;
    private AddressPresenter addressPresenter = new AddressPresenter();
    private ProgressBar progressBar;
    private RelativeLayout boxProgressBar;
    private ImageButton btnBackAddAddress;
    private ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            Bundle bundle = intent.getExtras();
                            if (bundle != null) {
                                province = (Province) bundle.getSerializable("province");
                                district = (Province) bundle.getSerializable("district");
                                ward = (Province) bundle.getSerializable("ward");
                                txtAddress.setTextColor(Color.BLACK);
                                txtAddress.setText(province.getName() + "/" + district.getName() + "/" + ward.getName());

                            }
                            boxProgressBar.setVisibility(View.GONE);

                        }
                    });

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initUi();
        handleAction();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleAction() {
        btnCheckout.setOnClickListener(v -> {
//            CheckoutDialog cdd=new CheckoutDialog(AddAddressActivity.this);
//            cdd.show();
            boxProgressBar.setVisibility(View.VISIBLE);
            if (edtName.getText().toString().trim().isEmpty()) {
                edtName.setHintTextColor(Color.RED);
            }
            if (edtPhoneNumber.getText().toString().trim().isEmpty()) {
                edtPhoneNumber.setHintTextColor(Color.RED);
            }
            if (edtNumberTree.getText().toString().trim().isEmpty()) {
                edtNumberTree.setHintTextColor(Color.RED);
            }
            if(province == null || district == null || ward == null){
                txtAddress.setTextColor(Color.RED);
            }
            if (!edtName.getText().toString().trim().isEmpty() && !edtPhoneNumber.getText().toString().trim().isEmpty()
                    && !edtNumberTree.getText().toString().trim().isEmpty() && province != null
                    && district != null && ward != null) {

                String telePhone = edtPhoneNumber.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                String address = edtNumberTree.getText().toString();
                User user = DataLocalManager.getInstance().getUser();
                UserInfo userInfo = new UserInfo(user.getId(),telePhone,name,address,province.getName(),district.getName(),
                        ward.getName(),typeAddress+"",isDefault,0);
                UserInfo userInfo2 =  addressPresenter.insertAddress(userInfo);
                Intent intent = new Intent(AddAddressActivity.this,GetAddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userInfo",userInfo2);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
            boxProgressBar.setVisibility(View.GONE);


        });
        btnBoxSelect.setOnClickListener(v -> {
            boxProgressBar.setVisibility(View.VISIBLE);
            Intent intent = new Intent(AddAddressActivity.this, SelectAddressActivity.class);
            activityResultLauncher.launch(intent);
        });
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // 1 văn phòng, 2 nhà riêng
            typeAddress = checkedId;
        });
        swDefault.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            if (isChecked) {
                isDefault = 1;
            } else {
                isDefault = 0;
            }
        });
        btnBackAddAddress.setOnClickListener(v -> {
            Intent intent = new Intent(AddAddressActivity.this,GetAddressActivity.class);
            setResult(RESULT_OK,intent);
            finish();
        });
    }

    private void initUi() {
        btnCheckout = findViewById(R.id.btnCheckout);
        btnBoxSelect = findViewById(R.id.btnBoxSelect);
        txtAddress = findViewById(R.id.txtAddress);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtNumberTree = findViewById(R.id.edtNumberTree);
        radioGroup = findViewById(R.id.radioTypeAddress);
        swDefault = findViewById(R.id.swDefault);
        raNhaRieng = findViewById(R.id.raNhaRieng);
        progressBar = findViewById(R.id.progressBar);
        boxProgressBar = findViewById(R.id.boxProgressBar);
        btnBackAddAddress = findViewById(R.id.btnBackAddAddress);
        raNhaRieng.setChecked(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(AddAddressActivity.this,GetAddressActivity.class);
        setResult(RESULT_OK,intent);
        finish();
    }
}