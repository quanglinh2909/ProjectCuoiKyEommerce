package com.example.projectcuoikyeommerce.presenter;

import android.content.Context;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.component.RealPathUtil;
import com.example.projectcuoikyeommerce.dto.DataUpload;
import com.example.projectcuoikyeommerce.dto.ImageUpload;
import com.example.projectcuoikyeommerce.dto.ProductUpload;
import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.FileUload;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.Product;
import com.example.projectcuoikyeommerce.model.Province;
import com.example.projectcuoikyeommerce.model.TagParent;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddproductPresenter {
    private Context context;

    public AddproductPresenter(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<FileUload> uploadImage(List<Uri> uriList) {
        if (uriList.size() == 0) return null;
        try {
            CompletableFuture<List<FileUload>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.product().uploadFile(requestUploadSurvey(uriList)).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String createImage(List<ImageUpload> uriList) {
        try {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.image().create(uriList).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Product create(DataUpload productUpload) {
        try {
            CompletableFuture<Product> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.product().create(productUpload).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Product update(ProductUpload productUpload) {
        try {
            CompletableFuture<Product> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.product().update(productUpload).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });
            return future.get();
        } catch (Exception e) {
            return null;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<TagParent> getListType() {
        try {
            CompletableFuture<List<TagParent>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.tagParent().listTagParent().execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return future.get();
        } catch (Exception e) {
            return null;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public String deleteImage(String id,String url) {
        try {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.image().deleteImage(id,url).execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Branch> getListBST() {
        try {
            CompletableFuture<List<Branch>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return ApiUtils.banner().getListBrand().execute().body();
                } catch (IOException e) {
                    return null;
                }
            });

            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    private MultipartBody.Part[] requestUploadSurvey(List<Uri> uriList) {
        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[uriList.size()];
        for (int index = 0; index < uriList.size(); index++) {
            String path = RealPathUtil.getRealPath(context, uriList.get(index));
            File file = new File(path);
            RequestBody surveyBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            surveyImagesParts[index] = MultipartBody.Part.createFormData("files", file.getName(),
                    surveyBody);
        }
        return surveyImagesParts;

    }
}
