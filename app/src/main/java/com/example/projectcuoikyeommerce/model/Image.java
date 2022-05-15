package com.example.projectcuoikyeommerce.model;

public class Image {
    private Product product;
    private String urlImage;
    private String alt;

    public Image(Product product, String urlImage, String alt) {
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
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
