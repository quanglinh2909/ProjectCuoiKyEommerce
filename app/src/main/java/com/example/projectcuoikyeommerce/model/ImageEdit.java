package com.example.projectcuoikyeommerce.model;

import android.net.Uri;

public class ImageEdit {
    private String url;
    private Uri uri;
    private int type;
    private int id;

    public ImageEdit(String url, Uri uri, int type, int id) {
        this.url = url;
        this.uri = uri;
        this.type = type;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
