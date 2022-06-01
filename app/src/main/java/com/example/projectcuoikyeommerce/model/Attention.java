package com.example.projectcuoikyeommerce.model;

public class Attention {
    private int id;
    private String icon;
    private String note;

    public Attention(int id, String icon, String note) {
        this.id = id;
        this.icon = icon;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
