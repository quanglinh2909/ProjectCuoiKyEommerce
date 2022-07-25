package com.example.projectcuoikyeommerce.dto;

import com.example.projectcuoikyeommerce.model.Oder;

import java.util.List;

public class ArrayListOrder {
    private List<ProductCartDto> oderList;
    private String idUser;
    private String idAddress;
    private String nameUser;
    private double total;

    public ArrayListOrder(List<ProductCartDto> oderList, String idUser, String idAddress, double total,String nameUser) {
        this.oderList = oderList;
        this.idUser = idUser;
        this.idAddress = idAddress;
        this.total = total;
        this.nameUser = nameUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public List<ProductCartDto> getOderList() {
        return oderList;
    }

    public void setOderList(List<ProductCartDto> oderList) {
        this.oderList = oderList;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
