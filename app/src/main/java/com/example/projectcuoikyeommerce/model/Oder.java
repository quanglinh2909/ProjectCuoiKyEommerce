package com.example.projectcuoikyeommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Oder {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("oderDate")
    @Expose
    private String oderDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOderDate() {
        return oderDate;
    }

    public void setOderDate(String oderDate) {
        this.oderDate = oderDate;
    }
}
