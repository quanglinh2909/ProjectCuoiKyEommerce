package com.example.projectcuoikyeommerce.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailDto {

    @SerializedName("order_detail_entity_id")
    @Expose
    private Integer orderDetailEntityId;
    @SerializedName("order_detail_entity_price")
    @Expose
    private Integer orderDetailEntityPrice;
    @SerializedName("order_detail_entity_quantity")
    @Expose
    private Integer orderDetailEntityQuantity;
    @SerializedName("order_detail_entity_size")
    @Expose
    private String orderDetailEntitySize;
    @SerializedName("order_detail_entity_altDelete")
    @Expose
    private String orderDetailEntityAltDelete;
    @SerializedName("order_detail_entity_idProductId")
    @Expose
    private String orderDetailEntityIdProductId;
    @SerializedName("order_detail_entity_idOrderId")
    @Expose
    private String orderDetailEntityIdOrderId;
    @SerializedName("order_entity_id")
    @Expose
    private String orderEntityId;
    @SerializedName("order_entity_total")
    @Expose
    private Integer orderEntityTotal;
    @SerializedName("order_entity_status")
    @Expose
    private Integer orderEntityStatus;
    @SerializedName("order_entity_oderDate")
    @Expose
    private String orderEntityOderDate;
    @SerializedName("order_entity_idUserId")
    @Expose
    private Integer orderEntityIdUserId;
    @SerializedName("order_entity_idAddressId")
    @Expose
    private Integer orderEntityIdAddressId;
    @SerializedName("user_entity_id")
    @Expose
    private Integer userEntityId;
    @SerializedName("user_entity_userName")
    @Expose
    private String userEntityUserName;
    @SerializedName("user_entity_email")
    @Expose
    private String userEntityEmail;
    @SerializedName("user_entity_token")
    @Expose
    private String userEntityToken;
    @SerializedName("user_entity_role")
    @Expose
    private Integer userEntityRole;
    @SerializedName("user_entity_password")
    @Expose
    private String userEntityPassword;
    @SerializedName("product_entity_id")
    @Expose
    private String productEntityId;
    @SerializedName("product_entity_name")
    @Expose
    private String productEntityName;
    @SerializedName("product_entity_price")
    @Expose
    private Integer productEntityPrice;
    @SerializedName("product_entity_S")
    @Expose
    private Integer productEntityS;
    @SerializedName("product_entity_M")
    @Expose
    private Integer productEntityM;
    @SerializedName("product_entity_L")
    @Expose
    private Integer productEntityL;
    @SerializedName("product_entity_XL")
    @Expose
    private Integer productEntityXL;
    @SerializedName("product_entity_rating")
    @Expose
    private Integer productEntityRating;
    @SerializedName("product_entity_sale")
    @Expose
    private Integer productEntitySale;
    @SerializedName("product_entity_createdAt")
    @Expose
    private String productEntityCreatedAt;
    @SerializedName("product_entity_DeleteAt")
    @Expose
    private Object productEntityDeleteAt;
    @SerializedName("product_entity_idlocalbranchId")
    @Expose
    private Integer productEntityIdlocalbranchId;
    @SerializedName("product_entity_idParentId")
    @Expose
    private Integer productEntityIdParentId;
    @SerializedName("image_entity_id")
    @Expose
    private Integer imageEntityId;
    @SerializedName("image_entity_url")
    @Expose
    private String imageEntityUrl;
    @SerializedName("image_entity_idProductId")
    @Expose
    private String imageEntityIdProductId;
    @SerializedName("user_info_entity_id")
    @Expose
    private Integer userInfoEntityId;
    @SerializedName("user_info_entity_telephone")
    @Expose
    private String userInfoEntityTelephone;
    @SerializedName("user_info_entity_fullName")
    @Expose
    private String userInfoEntityFullName;
    @SerializedName("user_info_entity_address")
    @Expose
    private String userInfoEntityAddress;
    @SerializedName("user_info_entity_province")
    @Expose
    private String userInfoEntityProvince;
    @SerializedName("user_info_entity_district")
    @Expose
    private String userInfoEntityDistrict;
    @SerializedName("user_info_entity_ward")
    @Expose
    private String userInfoEntityWard;
    @SerializedName("user_info_entity_typeAddress")
    @Expose
    private String userInfoEntityTypeAddress;
    @SerializedName("user_info_entity_default")
    @Expose
    private Integer userInfoEntityDefault;
    @SerializedName("user_info_entity_sex")
    @Expose
    private Integer userInfoEntitySex;
    @SerializedName("user_info_entity_idUserId")
    @Expose
    private Integer userInfoEntityIdUserId;

    public Integer getOrderDetailEntityId() {
        return orderDetailEntityId;
    }

    public void setOrderDetailEntityId(Integer orderDetailEntityId) {
        this.orderDetailEntityId = orderDetailEntityId;
    }

    public Integer getOrderDetailEntityPrice() {
        return orderDetailEntityPrice;
    }

    public void setOrderDetailEntityPrice(Integer orderDetailEntityPrice) {
        this.orderDetailEntityPrice = orderDetailEntityPrice;
    }

    public Integer getOrderDetailEntityQuantity() {
        return orderDetailEntityQuantity;
    }

    public void setOrderDetailEntityQuantity(Integer orderDetailEntityQuantity) {
        this.orderDetailEntityQuantity = orderDetailEntityQuantity;
    }

    public String getOrderDetailEntitySize() {
        return orderDetailEntitySize;
    }

    public void setOrderDetailEntitySize(String orderDetailEntitySize) {
        this.orderDetailEntitySize = orderDetailEntitySize;
    }

    public String getOrderDetailEntityAltDelete() {
        return orderDetailEntityAltDelete;
    }

    public void setOrderDetailEntityAltDelete(String orderDetailEntityAltDelete) {
        this.orderDetailEntityAltDelete = orderDetailEntityAltDelete;
    }

    public String getOrderDetailEntityIdProductId() {
        return orderDetailEntityIdProductId;
    }

    public void setOrderDetailEntityIdProductId(String orderDetailEntityIdProductId) {
        this.orderDetailEntityIdProductId = orderDetailEntityIdProductId;
    }

    public String getOrderDetailEntityIdOrderId() {
        return orderDetailEntityIdOrderId;
    }

    public void setOrderDetailEntityIdOrderId(String orderDetailEntityIdOrderId) {
        this.orderDetailEntityIdOrderId = orderDetailEntityIdOrderId;
    }

    public String getOrderEntityId() {
        return orderEntityId;
    }

    public void setOrderEntityId(String orderEntityId) {
        this.orderEntityId = orderEntityId;
    }

    public Integer getOrderEntityTotal() {
        return orderEntityTotal;
    }

    public void setOrderEntityTotal(Integer orderEntityTotal) {
        this.orderEntityTotal = orderEntityTotal;
    }

    public Integer getOrderEntityStatus() {
        return orderEntityStatus;
    }

    public void setOrderEntityStatus(Integer orderEntityStatus) {
        this.orderEntityStatus = orderEntityStatus;
    }

    public String getOrderEntityOderDate() {
        return orderEntityOderDate;
    }

    public void setOrderEntityOderDate(String orderEntityOderDate) {
        this.orderEntityOderDate = orderEntityOderDate;
    }

    public Integer getOrderEntityIdUserId() {
        return orderEntityIdUserId;
    }

    public void setOrderEntityIdUserId(Integer orderEntityIdUserId) {
        this.orderEntityIdUserId = orderEntityIdUserId;
    }

    public Integer getOrderEntityIdAddressId() {
        return orderEntityIdAddressId;
    }

    public void setOrderEntityIdAddressId(Integer orderEntityIdAddressId) {
        this.orderEntityIdAddressId = orderEntityIdAddressId;
    }

    public Integer getUserEntityId() {
        return userEntityId;
    }

    public void setUserEntityId(Integer userEntityId) {
        this.userEntityId = userEntityId;
    }

    public String getUserEntityUserName() {
        return userEntityUserName;
    }

    public void setUserEntityUserName(String userEntityUserName) {
        this.userEntityUserName = userEntityUserName;
    }

    public String getUserEntityEmail() {
        return userEntityEmail;
    }

    public void setUserEntityEmail(String userEntityEmail) {
        this.userEntityEmail = userEntityEmail;
    }

    public String getUserEntityToken() {
        return userEntityToken;
    }

    public void setUserEntityToken(String userEntityToken) {
        this.userEntityToken = userEntityToken;
    }

    public Integer getUserEntityRole() {
        return userEntityRole;
    }

    public void setUserEntityRole(Integer userEntityRole) {
        this.userEntityRole = userEntityRole;
    }

    public String getUserEntityPassword() {
        return userEntityPassword;
    }

    public void setUserEntityPassword(String userEntityPassword) {
        this.userEntityPassword = userEntityPassword;
    }

    public String getProductEntityId() {
        return productEntityId;
    }

    public void setProductEntityId(String productEntityId) {
        this.productEntityId = productEntityId;
    }

    public String getProductEntityName() {
        return productEntityName;
    }

    public void setProductEntityName(String productEntityName) {
        this.productEntityName = productEntityName;
    }

    public Integer getProductEntityPrice() {
        return productEntityPrice;
    }

    public void setProductEntityPrice(Integer productEntityPrice) {
        this.productEntityPrice = productEntityPrice;
    }

    public Integer getProductEntityS() {
        return productEntityS;
    }

    public void setProductEntityS(Integer productEntityS) {
        this.productEntityS = productEntityS;
    }

    public Integer getProductEntityM() {
        return productEntityM;
    }

    public void setProductEntityM(Integer productEntityM) {
        this.productEntityM = productEntityM;
    }

    public Integer getProductEntityL() {
        return productEntityL;
    }

    public void setProductEntityL(Integer productEntityL) {
        this.productEntityL = productEntityL;
    }

    public Integer getProductEntityXL() {
        return productEntityXL;
    }

    public void setProductEntityXL(Integer productEntityXL) {
        this.productEntityXL = productEntityXL;
    }

    public Integer getProductEntityRating() {
        return productEntityRating;
    }

    public void setProductEntityRating(Integer productEntityRating) {
        this.productEntityRating = productEntityRating;
    }

    public Integer getProductEntitySale() {
        return productEntitySale;
    }

    public void setProductEntitySale(Integer productEntitySale) {
        this.productEntitySale = productEntitySale;
    }

    public String getProductEntityCreatedAt() {
        return productEntityCreatedAt;
    }

    public void setProductEntityCreatedAt(String productEntityCreatedAt) {
        this.productEntityCreatedAt = productEntityCreatedAt;
    }

    public Object getProductEntityDeleteAt() {
        return productEntityDeleteAt;
    }

    public void setProductEntityDeleteAt(Object productEntityDeleteAt) {
        this.productEntityDeleteAt = productEntityDeleteAt;
    }

    public Integer getProductEntityIdlocalbranchId() {
        return productEntityIdlocalbranchId;
    }

    public void setProductEntityIdlocalbranchId(Integer productEntityIdlocalbranchId) {
        this.productEntityIdlocalbranchId = productEntityIdlocalbranchId;
    }

    public Integer getProductEntityIdParentId() {
        return productEntityIdParentId;
    }

    public void setProductEntityIdParentId(Integer productEntityIdParentId) {
        this.productEntityIdParentId = productEntityIdParentId;
    }

    public Integer getImageEntityId() {
        return imageEntityId;
    }

    public void setImageEntityId(Integer imageEntityId) {
        this.imageEntityId = imageEntityId;
    }

    public String getImageEntityUrl() {
        return imageEntityUrl;
    }

    public void setImageEntityUrl(String imageEntityUrl) {
        this.imageEntityUrl = imageEntityUrl;
    }

    public String getImageEntityIdProductId() {
        return imageEntityIdProductId;
    }

    public void setImageEntityIdProductId(String imageEntityIdProductId) {
        this.imageEntityIdProductId = imageEntityIdProductId;
    }

    public Integer getUserInfoEntityId() {
        return userInfoEntityId;
    }

    public void setUserInfoEntityId(Integer userInfoEntityId) {
        this.userInfoEntityId = userInfoEntityId;
    }

    public String getUserInfoEntityTelephone() {
        return userInfoEntityTelephone;
    }

    public void setUserInfoEntityTelephone(String userInfoEntityTelephone) {
        this.userInfoEntityTelephone = userInfoEntityTelephone;
    }

    public String getUserInfoEntityFullName() {
        return userInfoEntityFullName;
    }

    public void setUserInfoEntityFullName(String userInfoEntityFullName) {
        this.userInfoEntityFullName = userInfoEntityFullName;
    }

    public String getUserInfoEntityAddress() {
        return userInfoEntityAddress;
    }

    public void setUserInfoEntityAddress(String userInfoEntityAddress) {
        this.userInfoEntityAddress = userInfoEntityAddress;
    }

    public String getUserInfoEntityProvince() {
        return userInfoEntityProvince;
    }

    public void setUserInfoEntityProvince(String userInfoEntityProvince) {
        this.userInfoEntityProvince = userInfoEntityProvince;
    }

    public String getUserInfoEntityDistrict() {
        return userInfoEntityDistrict;
    }

    public void setUserInfoEntityDistrict(String userInfoEntityDistrict) {
        this.userInfoEntityDistrict = userInfoEntityDistrict;
    }

    public String getUserInfoEntityWard() {
        return userInfoEntityWard;
    }

    public void setUserInfoEntityWard(String userInfoEntityWard) {
        this.userInfoEntityWard = userInfoEntityWard;
    }

    public String getUserInfoEntityTypeAddress() {
        return userInfoEntityTypeAddress;
    }

    public void setUserInfoEntityTypeAddress(String userInfoEntityTypeAddress) {
        this.userInfoEntityTypeAddress = userInfoEntityTypeAddress;
    }

    public Integer getUserInfoEntityDefault() {
        return userInfoEntityDefault;
    }

    public void setUserInfoEntityDefault(Integer userInfoEntityDefault) {
        this.userInfoEntityDefault = userInfoEntityDefault;
    }

    public Integer getUserInfoEntitySex() {
        return userInfoEntitySex;
    }

    public void setUserInfoEntitySex(Integer userInfoEntitySex) {
        this.userInfoEntitySex = userInfoEntitySex;
    }

    public Integer getUserInfoEntityIdUserId() {
        return userInfoEntityIdUserId;
    }

    public void setUserInfoEntityIdUserId(Integer userInfoEntityIdUserId) {
        this.userInfoEntityIdUserId = userInfoEntityIdUserId;
    }

    @Override
    public String toString() {
        return "OrderDetailDto{" +
                "orderDetailEntityId=" + orderDetailEntityId +
                ", orderDetailEntityPrice=" + orderDetailEntityPrice +
                ", orderDetailEntityQuantity=" + orderDetailEntityQuantity +
                ", orderDetailEntitySize='" + orderDetailEntitySize + '\'' +
                ", orderDetailEntityAltDelete='" + orderDetailEntityAltDelete + '\'' +
                ", orderDetailEntityIdProductId='" + orderDetailEntityIdProductId + '\'' +
                ", orderDetailEntityIdOrderId='" + orderDetailEntityIdOrderId + '\'' +
                ", orderEntityId='" + orderEntityId + '\'' +
                ", orderEntityTotal=" + orderEntityTotal +
                ", orderEntityStatus=" + orderEntityStatus +
                ", orderEntityOderDate='" + orderEntityOderDate + '\'' +
                ", orderEntityIdUserId=" + orderEntityIdUserId +
                ", orderEntityIdAddressId=" + orderEntityIdAddressId +
                ", userEntityId=" + userEntityId +
                ", userEntityUserName='" + userEntityUserName + '\'' +
                ", userEntityEmail='" + userEntityEmail + '\'' +
                ", userEntityToken='" + userEntityToken + '\'' +
                ", userEntityRole=" + userEntityRole +
                ", userEntityPassword='" + userEntityPassword + '\'' +
                ", productEntityId='" + productEntityId + '\'' +
                ", productEntityName='" + productEntityName + '\'' +
                ", productEntityPrice=" + productEntityPrice +
                ", productEntityS=" + productEntityS +
                ", productEntityM=" + productEntityM +
                ", productEntityL=" + productEntityL +
                ", productEntityXL=" + productEntityXL +
                ", productEntityRating=" + productEntityRating +
                ", productEntitySale=" + productEntitySale +
                ", productEntityCreatedAt='" + productEntityCreatedAt + '\'' +
                ", productEntityDeleteAt=" + productEntityDeleteAt +
                ", productEntityIdlocalbranchId=" + productEntityIdlocalbranchId +
                ", productEntityIdParentId=" + productEntityIdParentId +
                ", imageEntityId=" + imageEntityId +
                ", imageEntityUrl='" + imageEntityUrl + '\'' +
                ", imageEntityIdProductId='" + imageEntityIdProductId + '\'' +
                ", userInfoEntityId=" + userInfoEntityId +
                ", userInfoEntityTelephone='" + userInfoEntityTelephone + '\'' +
                ", userInfoEntityFullName='" + userInfoEntityFullName + '\'' +
                ", userInfoEntityAddress='" + userInfoEntityAddress + '\'' +
                ", userInfoEntityProvince='" + userInfoEntityProvince + '\'' +
                ", userInfoEntityDistrict='" + userInfoEntityDistrict + '\'' +
                ", userInfoEntityWard='" + userInfoEntityWard + '\'' +
                ", userInfoEntityTypeAddress='" + userInfoEntityTypeAddress + '\'' +
                ", userInfoEntityDefault=" + userInfoEntityDefault +
                ", userInfoEntitySex=" + userInfoEntitySex +
                ", userInfoEntityIdUserId=" + userInfoEntityIdUserId +
                '}';
    }
}
