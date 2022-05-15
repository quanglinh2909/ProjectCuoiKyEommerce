package com.example.projectcuoikyeommerce.model;

public class Product {
    private String id;
    private Branch branch;
    private String name;
    private double price;
    private int S;
    private int M;
    private int L;
    private int XL;
    private Collection collection;
    private TagChild tagChild;

    public Product(String id, Branch branch, String name, double price, int s, int m, int l, int XL, Collection collection, TagChild tagChild) {
        this.id = id;
        this.branch = branch;
        this.name = name;
        this.price = price;
        S = s;
        M = m;
        L = l;
        this.XL = XL;
        this.collection = collection;
        this.tagChild = tagChild;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getS() {
        return S;
    }

    public void setS(int s) {
        S = s;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }

    public int getXL() {
        return XL;
    }

    public void setXL(int XL) {
        this.XL = XL;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public TagChild getTagChild() {
        return tagChild;
    }

    public void setTagChild(TagChild tagChild) {
        this.tagChild = tagChild;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", branch=" + branch +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", S=" + S +
                ", M=" + M +
                ", L=" + L +
                ", XL=" + XL +
                ", collection=" + collection +
                ", tagChild=" + tagChild +
                '}';
    }
}
