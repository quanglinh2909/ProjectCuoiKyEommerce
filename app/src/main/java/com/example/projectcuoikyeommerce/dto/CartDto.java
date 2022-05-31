package com.example.projectcuoikyeommerce.dto;

import com.example.projectcuoikyeommerce.model.Product;
import com.example.projectcuoikyeommerce.model.User;

public class CartDto {
    private Integer id;
    private Integer idUser;
    private String idProduct;
    private Integer quantity;
    private String size;

    public CartDto( Integer idUser, String idProduct, Integer quantity, String size) {
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.size = size;
    }

    public CartDto(Integer id, Integer idUser, String idProduct, Integer quantity, String size) {
        this.id = id;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "idUser='" + idUser + '\'' +
                ", idProduct='" + idProduct + '\'' +
                ", quantity=" + quantity +
                ", size='" + size + '\'' +
                '}';
    }
}
