package com.example.projectcuoikyeommerce.presenter;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.dto.ArrayListOrder;
import com.example.projectcuoikyeommerce.dto.CartDto;
import com.example.projectcuoikyeommerce.dto.OrderAdminDto;
import com.example.projectcuoikyeommerce.dto.OrderDetailDto;
import com.example.projectcuoikyeommerce.fcm.Data;
import com.example.projectcuoikyeommerce.fcm.RequestNotification;
import com.example.projectcuoikyeommerce.fcm.ResponseNotification;
import com.example.projectcuoikyeommerce.model.Oder;
import com.example.projectcuoikyeommerce.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OrderPresenter {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Oder order(ArrayListOrder order) {
        try {
            CompletableFuture<Oder> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.order().insert(order).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  null;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ResponseNotification senNotification(RequestNotification responseNotification) {
        try {
            CompletableFuture<ResponseNotification> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.notification().sendNotification(responseNotification).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  null;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Integer getQuantityProduct(String idOrder) {
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.order().getQuantityProduct(idOrder).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  null;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public User getUserById(int idUser) {
        try {
            CompletableFuture<User> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.user().getUserByID(idUser).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  null;
        }
    }
    public int totalPage(int total) {
        if (total % 10 == 0) {
            return total / 10;
        }
        return total / 10 + 1;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Integer getTotalPage(int status) {
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  totalPage(ApiUtils.order().getTotalOrderByStatus(status).execute().body());
                } catch (IOException e) {
                    return 0;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  0;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Integer updateStatus(String id,int status,int idUser) {
        User user = DataLocalManager.getInstance().getUser();
        if(user.getRole() == 1){
            User userSend = getUserById(idUser);
            if(userSend != null && !userSend.getToken().trim().isEmpty()){
                Data data = new Data(getTitle(status), getMessage(status),id);
                RequestNotification requestNotification = new RequestNotification(data, userSend.getToken());
                senNotification(requestNotification);
                writeNotificationFirebase(idUser);
            }
        }
        if(user.getRole() == 0){
            List<User> userList = getAllUser();
            for (User user1 : userList) {
                if (user1.getRole() == 1 && user1.getToken() != null && !user1.getToken().trim().isEmpty()) {
                    Data data = new Data(getTitle(status), user.getUserName()+" đã hủy đơn hàng",id);
                    RequestNotification requestNotification = new RequestNotification(data, user1.getToken());
                    senNotification(requestNotification);
                    writeNotificationFirebase(user1.getId());
                    break;
                }
            }

        }


        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  totalPage(ApiUtils.order().updateStatus(id,status,user.getRole()).execute().body());
                } catch (IOException e) {
                    return 0;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  0;
        }
    }
    public void writeNotificationFirebase(int id){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(id+"");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer value = dataSnapshot.getValue(Integer.class);
                if(value != null){
                    myRef.setValue(value+1);
                }else{
                    myRef.setValue(1);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public String getTitle(int status) {
        switch (status) {
            case 0:
                return "Đơn hàng mới";
            case 1:
                return "Đang giao hàng";
            case 2:
                return "Đã giao hàng";
            case 3:
                return "Hủy đơn hàng";
            default:
                return "";
        }
    }
    public String getMessage(int status) {
        switch (status) {
            case 1:
                return "Đơn hàng của bạn đang được giao";
            case 2:
                return "Đơn hàng của bạn đã được giao";
            case 3:
                return "Đơn hàng của bạn đã bị hủy";
            default:
                return "";
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<OrderAdminDto> getByStatus(int status,int page) {
        List<OrderAdminDto> list = new ArrayList<>();
        User user = DataLocalManager.getInstance().getUser();
        try {
            CompletableFuture<List<OrderAdminDto>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    if(user.getRole() == 0){
                        return  ApiUtils.order().getByStatus(status,page,10,
                                user.getId()).execute().body();
                    }else{
                        return  ApiUtils.order().getByStatus(status,page,10,-1).execute().body();
                    }

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
    public List<OrderDetailDto> getOrderById(String id) {
        List<OrderDetailDto> list = new ArrayList<>();
        try {
            CompletableFuture<List<OrderDetailDto>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.order().getOrderById(id).execute().body();
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
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        try {
            CompletableFuture<List<User>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.user().getAllUser().execute().body();
                } catch (IOException e) {
                    return list;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  list;
        }
    }
}
