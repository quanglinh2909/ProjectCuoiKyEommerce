package com.example.projectcuoikyeommerce.presenter;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.Product;
import com.example.projectcuoikyeommerce.model.TagChild;
import com.example.projectcuoikyeommerce.model.TagParent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CategoryPresenter {
    public int totalPage(int total) {
        if (total % 10 == 0) {
            return total / 10;
        }
        return total / 10 + 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Image> getListProduct(TagParent tagParent, TagChild tagChild, int page) {
        if (tagChild == null) return getListImagePaging(tagParent.getId(), (page * 10) - 10, 10);
        else return getByIdTagChild(tagParent.getId(), tagChild.getCode(), (page * 10) - 10, 10);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getTotalProduct(TagParent tagParent, TagChild tagChild) {

        if (tagChild == null) return getSumProduct(tagParent.getId());
        else return getTotalProductByTagChild(tagParent.getId(), tagChild.getCode());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getSumProduct() {
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.product().getSumProduct().execute().body();
                } catch (IOException e) {
                    return 0;
                }
            });

            return future.get();
        } catch (Exception e) {
            return 0;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Image> getListImagePaging(int id, int page, int limit) {
        List<Image> products = new ArrayList<>();
        try {
            CompletableFuture<List<Image>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.product().getListImagePaging(id, page, limit).execute().body();
                } catch (IOException e) {
                    return products;
                }
            });

            return future.get();
        } catch (Exception e) {
            return products;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Image> getAllProduct(int page, int limit) {
        List<Image> products = new ArrayList<>();
        try {
            CompletableFuture<List<Image>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.product().getAllProduct(page, limit).execute().body();
                } catch (IOException e) {
                    return products;
                }
            });

            return future.get();
        } catch (Exception e) {
            return products;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Image> getByIdTagChild(int idTagParent, int codeTagChild, int offset, int limit) {
        List<Image> products = new ArrayList<>();
        try {
            CompletableFuture<List<Image>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.product().getByIdTagChild(idTagParent, codeTagChild, offset, limit).execute().body();
                } catch (IOException e) {
                    return products;
                }
            });

            return future.get();
        } catch (Exception e) {
            return products;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getSumProduct(int id) {
        int sum = 0;
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.product().getSumProduct(id).execute().body();
                } catch (IOException e) {
                    return sum;
                }
            });

            return future.get();
        } catch (Exception e) {
            return sum;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getTotalProductByTagChild(int idParent, int code) {
        int sum = 0;
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.product().getTotalProductByTagChild(idParent, code).execute().body();
                } catch (IOException e) {
                    return sum;
                }
            });

            return future.get();
        } catch (Exception e) {
            return sum;
        }
    }

}
