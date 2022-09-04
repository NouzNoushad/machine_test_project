package com.example.machinetest;

import com.example.machinetest.Model.CategoriesModel;
import com.example.machinetest.Model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryInterface {
    @GET("v2/5ec39cba300000720039c1f6")
    Call<CategoriesModel> getCategories();
}
