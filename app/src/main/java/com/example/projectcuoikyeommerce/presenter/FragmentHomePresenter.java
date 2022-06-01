package com.example.projectcuoikyeommerce.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.Product;
import com.example.projectcuoikyeommerce.model.TagParent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FragmentHomePresenter {
    String TAG = "AAA";

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Banner> getListBanner() {
        List<Banner> bannerList = new ArrayList<>();
        try {
            CompletableFuture<List<Banner>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.banner().getListBanner().execute().body();
                } catch (IOException e) {
                    return bannerList;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  bannerList;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<TagParent> getListTagParent() {
        List<TagParent> tagParents = new ArrayList<>();
        try {
            CompletableFuture<List<TagParent>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.tagParent().listTagParent().execute().body();
                } catch (IOException e) {
                    return tagParents;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  tagParents;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Image> getListProductByIdTagParent(int id) {
        List<Image> products = new ArrayList<>();
        try {
            CompletableFuture<List<Image>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.product().getListProductByTagParent(id).execute().body();
                } catch (IOException e) {
                    return products;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  products;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Branch> getListBranch() {
        List<Branch> branches = new ArrayList<>();
        try {
            CompletableFuture<List<Branch>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return  ApiUtils.trademark().listTrademark().execute().body();
                } catch (IOException e) {
                    return branches;
                }
            });

            return  future.get();
        } catch (Exception e) {
            return  branches;
        }
    }

}
