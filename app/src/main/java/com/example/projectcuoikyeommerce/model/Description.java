package com.example.projectcuoikyeommerce.model;

public class Description {
    private String id;
    private Product product;
    private String title;
    private String description;

    public Description(String id, Product product, String title, String description) {
        this.id = id;
        this.product = product;
        this.title = title;
        this.description = description;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Description{" +
                "id='" + id + '\'' +
                ", product=" + product +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
