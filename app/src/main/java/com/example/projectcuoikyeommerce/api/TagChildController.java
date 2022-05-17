package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.model.TagChild;
import com.example.projectcuoikyeommerce.model.TagParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TagChildController {
    @GET("tag-child/get-all-tags-child")
    Call<List<TagChild>> getListTagChild();
}
