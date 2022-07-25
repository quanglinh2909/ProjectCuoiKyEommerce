package com.example.projectcuoikyeommerce.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.SelectAddressAdapter;
import com.example.projectcuoikyeommerce.event.AddressEvent;
import com.example.projectcuoikyeommerce.model.Province;
import com.example.projectcuoikyeommerce.presenter.AddressPresenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SelectAddressActivity extends AppCompatActivity implements AddressEvent {
    private AddressPresenter addressPresenter;
    private RecyclerView recyclerView;
    private SelectAddressAdapter addressAdapter;
    private List<Province> provinceList = new ArrayList<>();
    private RelativeLayout boxProvice, boxDistrict, boxWard;
    private TextView txtDotProvince, nameProvince, txtDotDistrict, txtNameDistrict, txtDotWard, txtNameWard, txtReset;
    private View lineHeightProvince, lineHeight22;
    private Province province, district, ward;
    private int typeItem = 0;
    private RelativeLayout boxProgressBar;
    private ImageButton btnBackAddAddress;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        initUI();
        getDataProvince();
        khoitao();
        event();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void event() {
        txtReset.setOnClickListener(v -> khoitao());
        boxProgressBar.setVisibility(View.GONE);
        btnBackAddAddress.setOnClickListener(v -> {
            Intent intent = new Intent(SelectAddressActivity.this,AddAddressActivity.class);
            setResult(RESULT_OK,intent);
            finish();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void khoitao() {
        province = null;
        district = null;
        ward = null;
        typeItem = 0;
        boxWard.setVisibility(View.GONE);
        boxDistrict.setVisibility(View.GONE);
        txtDotProvince.setBackgroundColor(Color.RED);
        nameProvince.setTextColor(Color.RED);
        nameProvince.setText("Chọn Tỉnh/Thành phố");
        lineHeightProvince.setVisibility(View.GONE);

        provinceList = addressPresenter.getProvince();
        addressAdapter = new SelectAddressAdapter(provinceList, this);
        recyclerView.setAdapter(addressAdapter);
        boxProgressBar.setVisibility(View.GONE);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getDataProvince() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initUI() {
        addressPresenter = new AddressPresenter();
        recyclerView = findViewById(R.id.recyclerViewAddress);
        txtDotProvince = findViewById(R.id.txtDotProvince);
        nameProvince = findViewById(R.id.nameProvince);
        txtDotDistrict = findViewById(R.id.txtDotDistrict);
        txtNameDistrict = findViewById(R.id.txtNameDistrict);
        txtDotWard = findViewById(R.id.txtDotWard);
        txtNameWard = findViewById(R.id.txtNameWard);
        boxProvice = findViewById(R.id.boxProvice);
        boxDistrict = findViewById(R.id.boxDistrict);
        boxWard = findViewById(R.id.boxWard);
        lineHeightProvince = findViewById(R.id.lineHeightProvince);
        lineHeight22 = findViewById(R.id.lineHeight22);
        txtReset = findViewById(R.id.txtReset);
        boxProgressBar = findViewById(R.id.boxProgressBar);
        btnBackAddAddress = findViewById(R.id.btnBackAddAddress);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void selectProvince(int position, Province province) {
        boxProgressBar.setVisibility(View.VISIBLE);
        if(typeItem == 0){
            setSelectProvince(position, province);
        }
        if(typeItem == 1){
            setSelectDistrict(position, province);
        }
        if(typeItem == 2){
            setSelectWard(position, province);

        }
        typeItem++;
        boxProgressBar.setVisibility(View.GONE);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setSelectWard(int position, Province province) {
     ward = province;
        Intent intent = new Intent(SelectAddressActivity.this,AddAddressActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("province",this.province);
        bundle.putSerializable("district",district);
        bundle.putSerializable("ward",ward);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setSelectDistrict(int position, Province province) {
        provinceList = addressPresenter.getWards(this.province.getCode()+"",province.getCode()+"");
        addressAdapter = new SelectAddressAdapter(provinceList, this);
        recyclerView.setAdapter(addressAdapter);
        district = province;
        txtDotDistrict.setBackgroundColor(Color.GRAY);
        txtNameDistrict.setTextColor(Color.BLACK);
        lineHeight22.setVisibility(View.VISIBLE);
        txtNameDistrict.setText(province.getName());

        boxWard.setVisibility(View.VISIBLE);
        txtNameWard.setTextColor(Color.RED);
        txtNameWard.setText("Chọn Phường/Xã");
        txtDotWard.setBackgroundColor(Color.RED);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setSelectProvince(int position, Province province) {
        provinceList.clear();
        provinceList = addressPresenter.getDistricts(province.getCode()+"");
        addressAdapter = new SelectAddressAdapter(provinceList, this);
        recyclerView.setAdapter(addressAdapter);
        this.province = province;

        txtDotProvince.setBackgroundColor(Color.GRAY);
        nameProvince.setTextColor(Color.BLACK);
        lineHeightProvince.setVisibility(View.VISIBLE);
        nameProvince.setText(province.getName());
        boxDistrict.setVisibility(View.VISIBLE);
        txtNameDistrict.setTextColor(Color.RED);
        txtNameDistrict.setText("Chọn Quận/Huyện");
        lineHeight22.setVisibility(View.GONE);
        txtDotDistrict.setBackgroundColor(Color.RED);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(SelectAddressActivity.this,AddAddressActivity.class);
        setResult(RESULT_OK,intent);
        finish();
    }
}