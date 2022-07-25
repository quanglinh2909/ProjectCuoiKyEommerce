package com.example.projectcuoikyeommerce.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.MainActivity;
import com.example.projectcuoikyeommerce.activity.admin.DetailOrderActivity;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.data_local.MyApplication;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

//        RemoteMessage.Notification notification = message.getNotification();
//        if(notification == null) return;
//
//        String strTitle = notification.getTitle();
//        String srtMesse = notification.getBody();

        Map<String,String> stringStringMap = message.getData();
        String strTitle = stringStringMap.get("title");
        String srtMesse = stringStringMap.get("mess");
        String id = stringStringMap.get("id");

        sendNotificationMess(strTitle,srtMesse,id);
    }

    private void sendNotificationMess( String strTitle, String srtMesse,String id) {
        Intent intent = new Intent(this, DetailOrderActivity.class);
        intent.putExtra("idOrder",id);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        int icon = R.mipmap.ic_launcher;
        if(strTitle.equals("Đơn hàng mới")){
            icon =R.drawable.ic_baseline_article_24;
        }
        if(strTitle.equals("Đang giao hàng")){
            icon =R.drawable.ic_baseline_departure_board_24;
        }
        if(strTitle.equals("Đã giao hàng")){
            icon =R.drawable.ic_baseline_clean_hands_24;
        }
        if(strTitle.equals("Hủy đơn hàng")){
            icon =R.drawable.ic_baseline_free_cancellation_24;
        }
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this, MyApplication.CHANEL_ID)
                .setContentTitle(strTitle)
                .setContentText(srtMesse)
                .setSmallIcon(icon)
                .setContentIntent(pendingIntent);

        Notification notification = noBuilder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null){
            Random random = new Random();
            notificationManager.notify(random.nextInt(),notification);
        }

    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        DataLocalManager.getInstance().setToken(token);
    }
}
