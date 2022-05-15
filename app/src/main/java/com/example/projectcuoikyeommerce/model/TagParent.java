package com.example.projectcuoikyeommerce.model;

public class TagParent {
    private String id;
    private String name;
    private int idNumber;

    public TagParent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagParent(String id, String name, int idNumber) {
        this.id = id;
        this.name = name;
        this.idNumber = idNumber;
    }

    public int getIdNumber() {
        return idNumber;
    }


    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
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

    @Override
    public String toString() {
        return "TagParent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
