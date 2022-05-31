package com.example.projectcuoikyeommerce.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductCartDto {
    @SerializedName("cart_entity_id")
    @Expose
    private Integer cartEntityId;
    @SerializedName("cart_entity_quantity")
    @Expose
    private Integer cartEntityQuantity;
    @SerializedName("cart_entity_size")
    @Expose
    private String cartEntitySize;
    @SerializedName("cart_entity_idProductId")
    @Expose
    private String cartEntityIdProductId;
    @SerializedName("cart_entity_idUserId")
    @Expose
    private Integer cartEntityIdUserId;
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
    @SerializedName("product_entity_idlocalbranchId")
    @Expose
    private Integer productEntityIdlocalbranchId;
    @SerializedName("product_entity_idColectionId")
    @Expose
    private Object productEntityIdColectionId;
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

    public Integer getCartEntityId() {
        return cartEntityId;
    }

    public void setCartEntityId(Integer cartEntityId) {
        this.cartEntityId = cartEntityId;
    }

    public Integer getCartEntityQuantity() {
        return cartEntityQuantity;
    }

    public void setCartEntityQuantity(Integer cartEntityQuantity) {
        this.cartEntityQuantity = cartEntityQuantity;
    }

    public String getCartEntitySize() {
        return cartEntitySize;
    }

    public void setCartEntitySize(String cartEntitySize) {
        this.cartEntitySize = cartEntitySize;
    }

    public String getCartEntityIdProductId() {
        return cartEntityIdProductId;
    }

    public void setCartEntityIdProductId(String cartEntityIdProductId) {
        this.cartEntityIdProductId = cartEntityIdProductId;
    }

    public Integer getCartEntityIdUserId() {
        return cartEntityIdUserId;
    }

    public void setCartEntityIdUserId(Integer cartEntityIdUserId) {
        this.cartEntityIdUserId = cartEntityIdUserId;
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

    public Integer getProductEntityIdlocalbranchId() {
        return productEntityIdlocalbranchId;
    }

    public void setProductEntityIdlocalbranchId(Integer productEntityIdlocalbranchId) {
        this.productEntityIdlocalbranchId = productEntityIdlocalbranchId;
    }

    public Object getProductEntityIdColectionId() {
        return productEntityIdColectionId;
    }

    public void setProductEntityIdColectionId(Object productEntityIdColectionId) {
        this.productEntityIdColectionId = productEntityIdColectionId;
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
}
