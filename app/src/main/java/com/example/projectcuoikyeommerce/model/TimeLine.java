package com.example.projectcuoikyeommerce.model;
public class TimeLine {
    private int img;
    private String header;
    private String description;

    public TimeLine(int img, String header, String description) {
        this.img = img;
        this.header = header;
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }
}
