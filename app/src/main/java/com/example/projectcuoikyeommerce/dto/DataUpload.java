package com.example.projectcuoikyeommerce.dto;

import com.example.projectcuoikyeommerce.model.Image;

import java.util.List;

public class DataUpload {
    private ProductUpload product;
    private List<Image> listImage;

    public DataUpload(ProductUpload product, List<Image> listImage) {
        this.product = product;
        this.listImage = listImage;
    }
}
