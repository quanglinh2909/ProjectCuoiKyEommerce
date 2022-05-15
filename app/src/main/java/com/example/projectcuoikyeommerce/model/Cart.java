package com.example.projectcuoikyeommerce.model;

public class Cart {
    private String id;
    private User user;
    private Product product;
    private int amount;
    private String size;
    private ColorProduct colorProduct;

    public Cart(String id, User user, Product product, int amount, String size, ColorProduct colorProduct) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.amount = amount;
        this.size = size;
        this.colorProduct = colorProduct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ColorProduct getColorProduct() {
        return colorProduct;
    }

    public void setColorProduct(ColorProduct colorProduct) {
        this.colorProduct = colorProduct;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", product=" + product +
                ", amount=" + amount +
                ", size='" + size + '\'' +
                ", colorProduct=" + colorProduct +
                '}';
    }
}
