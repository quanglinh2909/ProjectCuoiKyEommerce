package com.example.projectcuoikyeommerce.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.AboutActivity;
import com.example.projectcuoikyeommerce.activity.EditUserActivity;
import com.example.projectcuoikyeommerce.activity.HelpActivity;
import com.example.projectcuoikyeommerce.activity.LoginActivity;
import com.example.projectcuoikyeommerce.activity.OrderActivity;
import com.example.projectcuoikyeommerce.activity.admin.EditProductActivity;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.model.User;
import com.example.projectcuoikyeommerce.presenter.LoginPresenter;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    private RelativeLayout btnLogOut;
    private LoginPresenter loginPresenter = new LoginPresenter(getActivity());
    private View mView;
    private TextView btnXemThem, txtEmail, txtNameUser;
    private ImageView btnCHoXacNhan, btnDangGiao, btnDangGiaoHang, btnHuyDonHang;
    private RelativeLayout boxDanhGia, boxDaXemGanDay, boxThietLapTaiKhoan, boxTroGiup, boxThongTin;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, container, false);

        initUI();
        handleAction();
        User user = DataLocalManager.getInstance().getUser();
        txtEmail.setText(user.getEmail());
        txtNameUser.setText(user.getUserName());

        return mView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleAction() {
        btnLogOut.setOnClickListener(v -> {
            User user = loginPresenter.logOut();
            if (user == null) return;
            DataLocalManager.getInstance().setUser(null);
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finishAffinity();
        });
        btnXemThem.setOnClickListener(v -> {
            Intent intent = new Intent(mView.getContext(), OrderActivity.class);
            mView.getContext().startActivity(intent);
        });
        btnCHoXacNhan.setOnClickListener(v -> {
            eventOrder();
        });
        btnDangGiao.setOnClickListener(v -> {
            eventOrder();
        });
        btnDangGiaoHang.setOnClickListener(v -> {
            eventOrder();
        });
        btnHuyDonHang.setOnClickListener(v -> {
            eventOrder();
        });

        boxTroGiup.setOnClickListener(v -> {
            Intent intent = new Intent(mView.getContext(), HelpActivity.class);
            mView.getContext().startActivity(intent);
        });

        boxThongTin.setOnClickListener(v -> {
            Intent intent = new Intent(mView.getContext(), AboutActivity.class);
            mView.getContext().startActivity(intent);
        });
        boxThietLapTaiKhoan.setOnClickListener(v -> {
            Intent intent = new Intent(mView.getContext(), EditUserActivity.class);
            mView.getContext().startActivity(intent);
        });
    }

    private void initUI() {
        btnLogOut = mView.findViewById(R.id.btnLogOut);
        btnCHoXacNhan = mView.findViewById(R.id.btnCHoXacNhan);
        btnDangGiao = mView.findViewById(R.id.btnDangGiao);
        btnDangGiaoHang = mView.findViewById(R.id.btnDangGiaoHang);
        btnHuyDonHang = mView.findViewById(R.id.btnHuyDonHang);
        boxDanhGia = mView.findViewById(R.id.boxDanhGia);
        btnXemThem = mView.findViewById(R.id.btnXemThem);
        txtEmail = mView.findViewById(R.id.txtEmail);
        txtNameUser = mView.findViewById(R.id.txtNameUser);
        boxDaXemGanDay = mView.findViewById(R.id.boxDaXemGanDay);
        boxThietLapTaiKhoan = mView.findViewById(R.id.boxThietLapTaiKhoan);
        boxTroGiup = mView.findViewById(R.id.boxTroGiup);
        boxThongTin = mView.findViewById(R.id.boxThongTin);
    }

    private void eventOrder() {
        Intent intent = new Intent(mView.getContext(), OrderActivity.class);
        mView.getContext().startActivity(intent);
    }
}