package com.example.projectcuoikyeommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class TagChild {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("idTagParent")
    @Expose
    private TagParent idTagParent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TagParent getIdTagParent() {
        return idTagParent;
    }

    public void setIdTagParent(TagParent idTagParent) {
        this.idTagParent = idTagParent;
    }



    public TagChild(Integer id, String name, TagParent idTagParent) {
        this.id = id;
        this.name = name;
        this.idTagParent = idTagParent;
    }

    @Override
    public String toString() {
        return "TagChild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idTagParent=" + idTagParent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagChild tagChild = (TagChild) o;
        return Objects.equals(id, tagChild.id) && Objects.equals(name, tagChild.name) && Objects.equals(idTagParent, tagChild.idTagParent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, idTagParent);
    }
}
