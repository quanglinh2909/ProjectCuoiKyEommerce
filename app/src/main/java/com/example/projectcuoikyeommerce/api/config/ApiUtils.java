package com.example.projectcuoikyeommerce.api.config;

import com.example.projectcuoikyeommerce.api.BannerController;
import com.example.projectcuoikyeommerce.api.TagChildController;
import com.example.projectcuoikyeommerce.api.TagParentController;
import com.example.projectcuoikyeommerce.api.UserController;
import com.example.projectcuoikyeommerce.constant.URL;

public class ApiUtils {
    public static final String url = URL.BASE_URL+"api/";
    public static TagParentController tagParent(){
        return ConfigRetrofit.getClient(url).create(TagParentController.class);
    }
    public static TagChildController tagChild(){
        return ConfigRetrofit.getClient(url).create(TagChildController.class);
    }
    public static UserController user(){
        return ConfigRetrofit.getClient(url).create(UserController.class);
    }
    public static BannerController banner(){
        return ConfigRetrofit.getClient(url).create(BannerController.class);
    }
}
