package com.example.projectcuoikyeommerce.dto;

import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.Collection;
import com.example.projectcuoikyeommerce.model.TagParent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductUpload {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("S")
    @Expose
    private Integer s;
    @SerializedName("M")
    @Expose
    private Integer m;
    @SerializedName("L")
    @Expose
    private Integer l;
    @SerializedName("XL")
    @Expose
    private Integer xl;
    @SerializedName("idlocalbranch")
    @Expose
    private Integer idlocalbranch;
    @SerializedName("idParent")
    @Expose
    private Integer idParent;

    public ProductUpload(String name, Integer price, Integer s, Integer m, Integer l, Integer xl, Integer idlocalbranch, Integer idParent) {
        this.name = name;
        this.price = price;
        this.s = s;
        this.m = m;
        this.l = l;
        this.xl = xl;
        this.idlocalbranch = idlocalbranch;
        this.idParent = idParent;
    }

    public ProductUpload(String id, String name, Integer price, Integer s, Integer m, Integer l, Integer xl, Integer idlocalbranch, Integer idParent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.s = s;
        this.m = m;
        this.l = l;
        this.xl = xl;
        this.idlocalbranch = idlocalbranch;
        this.idParent = idParent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public Integer getM() {
        return m;
    }

    public void setM(Integer m) {
        this.m = m;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Integer getXl() {
        return xl;
    }

    public void setXl(Integer xl) {
        this.xl = xl;
    }

    public Integer getIdlocalbranch() {
        return idlocalbranch;
    }

    public void setIdlocalbranch(Integer idlocalbranch) {
        this.idlocalbranch = idlocalbranch;
    }

    public Integer getIdParent() {
        return idParent;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }
}
