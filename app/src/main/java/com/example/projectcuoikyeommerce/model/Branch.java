package com.example.projectcuoikyeommerce.model;

public class Branch {
    private String id;
    private String name;
    private String description;
    private String urlString;

    public Branch(String id, String name, String description, String urlString) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlString = urlString;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", urlString='" + urlString + '\'' +
                '}';
    }
}
