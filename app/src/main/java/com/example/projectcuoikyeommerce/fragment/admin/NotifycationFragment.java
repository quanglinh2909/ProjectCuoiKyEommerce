package com.example.projectcuoikyeommerce.fragment.admin;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.admin.NotifycationAdapter;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.model.Notifycation;
import com.example.projectcuoikyeommerce.presenter.NotificationPresenter;

import java.util.ArrayList;
import java.util.List;


public class NotifycationFragment extends Fragment {
    private View view;
    private RecyclerView recyclerVIewNotification;
    private NotificationPresenter presenter = new NotificationPresenter();
    private NotifycationAdapter notifycationAdapter;
    private List<Notifycation> notifycationList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notifycation, container, false);
        initUi();
        getData();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getData() {
        notifycationList = presenter.getByIdUser(DataLocalManager.getInstance().getUser().getId());
        notifycationAdapter = new NotifycationAdapter(notifycationList);
        recyclerVIewNotification.setAdapter(notifycationAdapter);
    }

    private void initUi() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerVIewNotification = view.findViewById(R.id.recyclerVIewNotification);
        recyclerVIewNotification.setLayoutManager(linearLayoutManager);
    }
}