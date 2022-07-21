package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.dto.DataUpload;
import com.example.projectcuoikyeommerce.dto.ProductUpload;
import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.FileUload;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.Product;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProductController {
    @GET("image/get-by-id-tag-parent/{id}")
    Call<List<Image>> getListProductByTagParent(@Path("id") int id);

    @GET("product/get-by-id/{id}")
    Call<Product> getProductById(@Path("id") String id);

    @GET("image/get-by-id-product-paging/{id}/{page}/{limit}")
    Call<List<Image>> getListImagePaging(@Path("id") int id, @Path("page") int page, @Path("limit") int limit);

    @GET("image/get-all-product/{page}/{limit}")
    Call<List<Image>> getAllProduct(@Path("page") int page, @Path("limit") int limit);

    @GET("product/get-sum-product")
    Call<Integer> getSumProduct();

    @GET("image/get-by-id-child/{idTagParent}/{codeTagChild}/{offset}/{limit}")
    Call<List<Image>> getByIdTagChild(@Path("idTagParent") int idTagParent, @Path("codeTagChild") int codeTagChild, @Path("offset") int offset, @Path("limit") int limit);

    @GET("product/get-count-parent/{id}")
    Call<Integer> getSumProduct(@Path("id") int id);

    @GET("product/get-total-by-id-child/{idTagParent}/{codeTagChild}")
    Call<Integer> getTotalProductByTagChild(@Path("idTagParent") int idTagParent, @Path("codeTagChild") int codeTagChild);

    @Multipart
    @POST("upload-file")
    Call<List<FileUload>> uploadFile(@Part MultipartBody.Part[] images);

    @POST("product/create")
    Call<Product> create(@Body DataUpload productUpload);

    @PUT("product/update")
    Call<Product> update(@Body ProductUpload productUpload);

    @DELETE("delete/{id}")
    Call<Product> delete(@Path("id") String id);


}
