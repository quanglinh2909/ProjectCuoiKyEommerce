package com.example.projectcuoikyeommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banner {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("linkImage")
    @Expose
    private String linkImage;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public Banner(Integer id, String linkImage, String createdAt) {
        this.id = id;
        this.linkImage = linkImage;
        this.createdAt = createdAt;
     }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", linkImage='" + linkImage + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
