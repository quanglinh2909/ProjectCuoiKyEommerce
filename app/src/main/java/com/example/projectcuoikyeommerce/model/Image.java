package com.example.projectcuoikyeommerce.model;

public class Image {
    private Product product;
    private int urlImage;
    private String alt;

    public Image(Product product, int urlImage, String alt) {
        this.product = product;
        this.urlImage = urlImage;
        this.alt = alt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(int urlImage) {
        this.urlImage = urlImage;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    public String toString() {
        return "Image{" +
                "product=" + product +
                ", urlImage='" + urlImage + '\'' +
                ", alt='" + alt + '\'' +
                '}';
    }
}
