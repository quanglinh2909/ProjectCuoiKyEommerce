package com.example.projectcuoikyeommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
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
    private Branch idlocalbranch;
    @SerializedName("idColection")
    @Expose
    private Collection idColection;
    @SerializedName("idParent")
    @Expose
    private TagParent idParent;

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

    public Branch getIdlocalbranch() {
        return idlocalbranch;
    }

    public void setIdlocalbranch(Branch idlocalbranch) {
        this.idlocalbranch = idlocalbranch;
    }

    public Collection getIdColection() {
        return idColection;
    }

    public void setIdColection(Collection idColection) {
        this.idColection = idColection;
    }

    public TagParent getIdParent() {
        return idParent;
    }

    public void setIdParent(TagParent idParent) {
        this.idParent = idParent;
    }


    public Product(String id, String name, Integer price, Integer s, Integer m, Integer l, Integer xl, Branch idlocalbranch, Collection idColection, TagParent idParent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.s = s;
        this.m = m;
        this.l = l;
        this.xl = xl;
        this.idlocalbranch = idlocalbranch;
        this.idColection = idColection;
        this.idParent = idParent;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", s=" + s +
                ", m=" + m +
                ", l=" + l +
                ", xl=" + xl +
                ", idlocalbranch=" + idlocalbranch +
                ", idColection=" + idColection +
                ", idParent=" + idParent +
                '}';
    }
}
