package com.example.projectcuoikyeommerce.model;

public class Size {
    private String name;
    private boolean isCheck;

    public Size(String name, boolean isCheck) {
        this.name = name;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "Size{" +
                "name='" + name + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
