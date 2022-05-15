package com.example.projectcuoikyeommerce.api.config;

import com.example.projectcuoikyeommerce.api.Controller;
import com.example.projectcuoikyeommerce.constant.URL;

public class ApiUtils {
    public static final String url = URL.BASE_URL+"api";
    public static Controller getData(){
        return ConfigRetrofit.getClient(url).create(Controller.class);
    }
}
