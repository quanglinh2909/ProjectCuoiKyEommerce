package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.dto.CartDto;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.Cart;
import com.example.projectcuoikyeommerce.model.Province;
import com.example.projectcuoikyeommerce.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserInfoController {
    @GET("user-info/get-address")
    Call<List<Province>> listProvince();

 @GET("user-info/by-user/{idUser}")
    Call<List<UserInfo>> getListAddress(@Path("idUser") int idUser);

    @GET("user-info/get-districts/{idProvince}")
    Call<List<Province>> listDistrict(@Path("idProvince") String idProvince);

    @GET("user-info/get-ward/{idProvince}/{idDistrict}")
    Call<List<Province>> listWard(@Path("idProvince") String idProvince,@Path("idDistrict") String idDistrict);

    @POST("user-info/insert")
    Call<UserInfo> insert(@Body UserInfo userInfo);
}
