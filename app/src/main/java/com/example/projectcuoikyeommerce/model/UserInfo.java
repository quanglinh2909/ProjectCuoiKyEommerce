package com.example.projectcuoikyeommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class UserInfo implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("idUser")
    @Expose
    private Integer idUser;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("ward")
    @Expose
    private String ward;
    @SerializedName("typeAddress")
    @Expose
    private String typeAddress;
    @SerializedName("default")
    @Expose
    private Integer _default;
    @SerializedName("sex")
    @Expose
    private Integer sex;

    public UserInfo(Integer idUser, String telephone, String fullName, String address,
                     String province, String district, String ward, String typeAddress,
                     Integer _default, Integer sex) {
        this.idUser = idUser;
        this.telephone = telephone;
        this.fullName = fullName;
        this.address = address;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.typeAddress = typeAddress;
        this._default = _default;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getTypeAddress() {
        return typeAddress;
    }

    public void setTypeAddress(String typeAddress) {
        this.typeAddress = typeAddress;
    }

    public Integer getDefault() {
        return _default;
    }

    public void setDefault(Integer _default) {
        this._default = _default;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id) && Objects.equals(idUser, userInfo.idUser) && Objects.equals(telephone, userInfo.telephone) && Objects.equals(fullName, userInfo.fullName) && Objects.equals(address, userInfo.address) && Objects.equals(province, userInfo.province) && Objects.equals(district, userInfo.district) && Objects.equals(ward, userInfo.ward) && Objects.equals(typeAddress, userInfo.typeAddress) && Objects.equals(_default, userInfo._default) && Objects.equals(sex, userInfo.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, telephone, fullName, address, province, district, ward, typeAddress, _default, sex);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", telephone='" + telephone + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", ward='" + ward + '\'' +
                ", typeAddress='" + typeAddress + '\'' +
                ", _default=" + _default +
                ", sex=" + sex +
                '}';
    }
}
