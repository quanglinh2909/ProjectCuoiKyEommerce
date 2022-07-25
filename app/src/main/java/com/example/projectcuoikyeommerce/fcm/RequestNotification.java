package com.example.projectcuoikyeommerce.fcm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestNotification {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("to")
    @Expose
    private String to;

    public RequestNotification(Data data, String to) {
        this.data = data;
        this.to = to;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
