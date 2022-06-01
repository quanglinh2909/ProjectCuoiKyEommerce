package com.example.projectcuoikyeommerce.model;

public class Cart {
    private String id;
    private User idUser;
    private Product idProduct;
    private int quantity;
    private String size;

    public Cart( User idUser, Product product, int amount, String size) {
        this.idUser = idUser;
        this.idProduct = product;
        this.quantity = amount;
        this.size = size;
    }

    public Cart(String id, User idUser, Product idProduct, int quantity, String size) {
        this.id = id;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Product getProduct() {
        return idProduct;
    }

    public void setProduct(Product product) {
        this.idProduct = product;
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
        return "Cart{" +
                "id='" + id + '\'' +
                ", user=" + idUser +
                ", product=" + idProduct +
                ", amount=" + quantity +
                ", size='" + size + '\'' +
                '}';
    }
}
