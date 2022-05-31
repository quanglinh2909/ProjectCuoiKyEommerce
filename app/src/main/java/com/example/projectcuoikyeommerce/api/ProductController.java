package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductController {
    @GET("image/get-by-id-tag-parent/{id}")
    Call<List<Image>> getListProductByTagParent(@Path("id") int id);

    @GET("product/get-by-id/{id}")
    Call<Product> getProductById(@Path("id") String id);

    @GET("image/get-by-id-product-paging/{id}/{page}/{limit}")
    Call<List<Image>> getListImagePaging(@Path("id") int id,@Path("page") int page,@Path("limit") int limit);

    @GET("image/get-by-id-child/{idTagParent}/{codeTagChild}/{offset}/{limit}")
    Call<List<Image>> getByIdTagChild(@Path("idTagParent") int idTagParent,@Path("codeTagChild") int codeTagChild,@Path("offset") int offset,@Path("limit") int limit);

    @GET("product/get-count-parent/{id}")
    Call<Integer> getSumProduct(@Path("id") int id);

    @GET("product/get-total-by-id-child/{idTagParent}/{codeTagChild}")
    Call<Integer> getTotalProductByTagChild(@Path("idTagParent") int idTagParent,@Path("codeTagChild") int codeTagChild);
}
