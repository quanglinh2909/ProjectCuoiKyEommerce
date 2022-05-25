package com.example.projectcuoikyeommerce.data_local;

import android.content.Context;

import com.example.projectcuoikyeommerce.constant.SharedPreferencesKey;
import com.example.projectcuoikyeommerce.model.User;
import com.google.gson.Gson;

public class DataLocalManager {
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;
    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }
    public static DataLocalManager getInstance(){
        if(instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }
    public  void setUser(User user){
        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);
        instance.mySharedPreferences.putStringValue(SharedPreferencesKey.KEY_USER,jsonUser);

    }
    public  User getUser(){
        Gson gson = new Gson();
        String jsonUser = instance.mySharedPreferences.getStringValue(SharedPreferencesKey.KEY_USER);
        User user = gson.fromJson(jsonUser,User.class);
        return  user;
    }
}
