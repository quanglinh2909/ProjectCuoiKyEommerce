package com.example.projectcuoikyeommerce.model;

public class Banner {
    private int url;
    private String description;

    public Banner(int url, String description) {
        this.url = url;
        this.description = description;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
