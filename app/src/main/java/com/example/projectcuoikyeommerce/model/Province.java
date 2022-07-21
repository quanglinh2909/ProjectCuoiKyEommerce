package com.example.projectcuoikyeommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Province implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Province{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}

