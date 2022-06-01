package com.example.projectcuoikyeommerce.model;

import java.sql.Date;

public class Oder {
    private String id;
    private User user;
    // .....
    private int status;
    private Date oderDate;
    private double total;

    public Oder(String id, User user, int status, Date oderDate, double total) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.oderDate = oderDate;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getOderDate() {
        return oderDate;
    }

    public void setOderDate(Date oderDate) {
        this.oderDate = oderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Oder{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", status=" + status +
                ", oderDate=" + oderDate +
                ", total=" + total +
                '}';
    }
}
