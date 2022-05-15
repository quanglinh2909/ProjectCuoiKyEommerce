package com.example.projectcuoikyeommerce.model;

public class UserInfo {
    private String id;
    private String idUser;
    private int age;
    // gioi tinh 0 la nam, nu la 1
    private boolean sex;
    private String telephone;
    private String firstName;
    private String lastName;

    public UserInfo(String id, String idUser, int age, boolean sex, String telephone, String firstName, String lastName) {
        this.id = id;
        this.idUser = idUser;
        this.age = age;
        this.sex = sex;
        this.telephone = telephone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", idUser='" + idUser + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", telephone='" + telephone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
