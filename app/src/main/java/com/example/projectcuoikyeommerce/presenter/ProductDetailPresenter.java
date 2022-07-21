package com.example.projectcuoikyeommerce.presenter;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.dto.CartDto;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.Cart;
import com.example.projectcuoikyeommerce.model.Description;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.Product;
import com.example.projectcuoikyeommerce.model.Size;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProductDetailPresenter {
    String TAG = "AAA";
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Image> getListImage(String id) {
        List<Image> imageList = new ArrayList<>();
        try {
            CompletableFuture<List<Image>> future = CompletableFuture.supplyAsync(() -> {
                try {

                    return  ApiUtils.image().getListImage(id).execute().body();
                } catch (IOException e) {
                    return imageList;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  imageList;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Product getProductById(String id) {
        try {
            CompletableFuture<Product> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.product().getProductById(id).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  null;
        }
    }
    public List<Size> getListSize(Product product){
        List<Size> sizes = new ArrayList<>();
        if (product == null) return sizes;
        if(product.getS() > 0){
            sizes.add(new Size("S",false));
        }
        if(product.getM() > 0){
            sizes.add(new Size("M",false));
        }
        if(product.getL() > 0){
            sizes.add(new Size("L",false));
        }
        if(product.getXl() > 0){
            sizes.add(new Size("XL",false));
        }
        return  sizes;

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Description> getDescription(String id) {
        List<Description> descriptionList = new ArrayList<>();
        try {
            CompletableFuture<List<Description>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.description().getListDescription(id).execute().body();
                } catch (IOException e) {
                    return descriptionList;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  descriptionList;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Cart insertCard(CartDto cart) {
        try {
            CompletableFuture<Cart> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.cart().insertCart(cart).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  null;
        }
    }


}
