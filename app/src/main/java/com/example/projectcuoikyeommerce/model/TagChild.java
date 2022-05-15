package com.example.projectcuoikyeommerce.model;

public class TagChild {
    private String id;
    private String idTagParent;
    private String name;
    private int idNumber;

    public TagChild(String id, String idTagParent, String name, int idNumber) {
        this.id = id;
        this.idTagParent = idTagParent;
        this.name = name;
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIdTagParent() {
        return idTagParent;
    }

    public void setIdTagParent(String idTagParent) {
        this.idTagParent = idTagParent;
    }

    @Override
    public String toString() {
        return "TagChild{" +
                "id='" + id + '\'' +
                ", idTagParent='" + idTagParent + '\'' +
                '}';
    }
}
