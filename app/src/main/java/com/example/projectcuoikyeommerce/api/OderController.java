package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.dto.ArrayListOrder;
import com.example.projectcuoikyeommerce.dto.CartDto;
import com.example.projectcuoikyeommerce.dto.OrderAdminDto;
import com.example.projectcuoikyeommerce.dto.OrderDetailDto;
import com.example.projectcuoikyeommerce.model.Cart;
import com.example.projectcuoikyeommerce.model.Oder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OderController {
    @POST("order/insert")
    Call<Oder> insert(@Body ArrayListOrder arrayListOrder);

    @GET("oder-detail/get-by-status/{status}/{page}/{limit}/{idUser}")
    Call<List<OrderAdminDto>> getByStatus(@Path("status") int status, @Path("page") int page, @Path("limit") int limit,@Path("idUser") int idUser);
 @GET("oder-detail/get-order-by-id/{id}")
    Call<List<OrderDetailDto>> getOrderById(@Path("id") String id);

    @GET("oder-detail/get-sum-procut/{idOrder}")
    Call<Integer> getQuantityProduct(@Path("idOrder") String idOrder);


    @GET("order/total-page/{status}")
    Call<Integer> getTotalOrderByStatus(@Path("status") int status);

    @PUT("order/update-status/{id}/{status}/{role}")
    Call<Integer> updateStatus(@Path("id") String id,@Path("status") int status,@Path("role") int role);

}
