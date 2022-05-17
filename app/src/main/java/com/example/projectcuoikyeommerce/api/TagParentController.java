package com.example.projectcuoikyeommerce.api;

import com.example.projectcuoikyeommerce.model.TagParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TagParentController {
    @GET("tag-parent/get-all-tags-parent")
    Call<List<TagParent>> listTagParent();

}
