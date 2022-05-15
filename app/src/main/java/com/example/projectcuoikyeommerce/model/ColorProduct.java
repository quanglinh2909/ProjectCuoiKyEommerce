package com.example.projectcuoikyeommerce.model;

public class ColorProduct {
    private String id;
    private Product product;
    private String nameColor;
    private int stocl;

    public ColorProduct(String id, Product product, String nameColor, int stocl) {
        this.id = id;
        this.product = product;
        this.nameColor = nameColor;
        this.stocl = stocl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

    public int getStocl() {
        return stocl;
    }

    public void setStocl(int stocl) {
        this.stocl = stocl;
    }

    @Override
    public String toString() {
        return "ColorProduct{" +
                "id='" + id + '\'' +
                ", product=" + product +
                ", nameColor='" + nameColor + '\'' +
                ", stocl=" + stocl +
                '}';
    }
}
