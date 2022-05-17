package com.example.projectcuoikyeommerce.api.config;

import com.example.projectcuoikyeommerce.api.TagChildController;
import com.example.projectcuoikyeommerce.api.TagParentController;
import com.example.projectcuoikyeommerce.constant.URL;

public class ApiUtils {
    public static final String url = URL.BASE_URL+"api/";
    public static TagParentController getDataTagParent(){
        return ConfigRetrofit.getClient(url).create(TagParentController.class);
    }
    public static TagChildController getDataTagChild(){
        return ConfigRetrofit.getClient(url).create(TagChildController.class);
    }
}
