package com.example.projectcuoikyeommerce.fcm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("mess")
    @Expose
    private String mess;

    @SerializedName("id")
    @Expose
    private String id;

    public Data(String title, String mess, String id) {
        this.title = title;
        this.mess = mess;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
