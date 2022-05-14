package com.example.projectcuoikyeommerce.api.config;

import com.example.projectcuoikyeommerce.api.Controller;

public class ApiUtils {
    public static final String url ="";
    public static Controller getData(){
        return ConfigRetrofit.getClient(url).create(Controller.class);
    }
}
