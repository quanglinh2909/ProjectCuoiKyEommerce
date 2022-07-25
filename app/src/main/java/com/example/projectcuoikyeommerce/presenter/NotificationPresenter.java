package com.example.projectcuoikyeommerce.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.dto.OrderAdminDto;
import com.example.projectcuoikyeommerce.model.Notifycation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NotificationPresenter {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Notifycation> getByIdUser(int idUser) {
        List<Notifycation> list = new ArrayList<>();
        try {
            CompletableFuture<List<Notifycation>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.notifycation().getByIdUser(idUser).execute().body();
                } catch (IOException e) {
                    return list;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  list;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Notifycation setStatus(int id) {
        try {
            CompletableFuture<Notifycation> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.notifycation().setStatus(id).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  null;
        }
    }
    public void writeNotificationFirebase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(DataLocalManager.getInstance().getUser().getId() +"");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer value = dataSnapshot.getValue(Integer.class);
                if(value != null){
                    myRef.setValue(value-1);
                }else{
                    myRef.setValue(0);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
