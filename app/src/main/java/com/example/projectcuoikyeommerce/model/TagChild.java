package com.example.projectcuoikyeommerce.model;

public class TagChild {
    private String id;
    private String idTagParent;

    public TagChild(String id, String idTagParent) {
        this.id = id;
        this.idTagParent = idTagParent;
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
