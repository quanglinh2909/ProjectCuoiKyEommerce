package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.fcm.RequestNotification;
import com.example.projectcuoikyeommerce.fcm.ResponseNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SendNotificationController {
    @Headers({
            "Authorization: key=AAAAbIS5-Zo:APA91bEVhE5YOQ7z8prreXfEYQ4RPB8LqAN4ZrZbV8bKu-UAtohKPqg0_GfUwoGT-zkonrPQA3oKWdSgbK9rToWFGl0CD2yOP-OOkZ-YATwRQh5deq7ZXRwcKEe85V3m_IgZFB-79bzH",
            "Content-Type: application/json"
    })
    @POST("fcm/send")
    Call<ResponseNotification> sendNotification(@Body RequestNotification requestNotification);
}
