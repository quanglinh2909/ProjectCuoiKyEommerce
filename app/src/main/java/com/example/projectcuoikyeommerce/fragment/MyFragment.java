package com.example.projectcuoikyeommerce.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.LoginActivity;
import com.example.projectcuoikyeommerce.activity.OrderActivity;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.model.User;
import com.example.projectcuoikyeommerce.presenter.LoginPresenter;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    private RelativeLayout btnLogOut;
    private LoginPresenter loginPresenter = new LoginPresenter(getActivity());
    private View mView;
    private TextView btnXemThem,txtEmail,txtNameUser;


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
           User user =  loginPresenter.logOut();
           if(user == null) return;
            DataLocalManager.getInstance().setUser(null);
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finishAffinity();
        });
        btnXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mView.getContext(), OrderActivity.class);
                mView.getContext().startActivity(intent);
            }
        });
    }

    private void initUI() {
        btnLogOut = mView.findViewById(R.id.btnLogOut);
        btnXemThem = mView.findViewById(R.id.btnXemThem);
        txtEmail = mView.findViewById(R.id.txtEmail);
        txtNameUser = mView.findViewById(R.id.txtNameUser);
    }
}