package com.example.projectcuoikyeommerce.api.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String baseUrl){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(15000, TimeUnit.MICROSECONDS)
                .writeTimeout(15000,TimeUnit.MICROSECONDS)
                .connectTimeout(15000,TimeUnit.MICROSECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return  retrofit;

    }

}
