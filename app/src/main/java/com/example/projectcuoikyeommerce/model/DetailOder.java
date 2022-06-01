package com.example.projectcuoikyeommerce.model;

public class DetailOder {
    private String idOder;
    private Product product;
    private int amount;
    private String size;
    private ColorProduct color;
    private double price;

    public DetailOder(String idOder, Product product, int amount, String size, ColorProduct color, double price) {
        this.idOder = idOder;
        this.product = product;
        this.amount = amount;
        this.size = size;
        this.color = color;
        this.price = price;
    }

    public String getIdOder() {
        return idOder;
    }

    public void setIdOder(String idOder) {
        this.idOder = idOder;
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

    public ColorProduct getColor() {
        return color;
    }

    public void setColor(ColorProduct color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DetailOder{" +
                "idOder='" + idOder + '\'' +
                ", product=" + product +
                ", amount=" + amount +
                ", size='" + size + '\'' +
                ", color=" + color +
                ", price=" + price +
                '}';
    }
}
