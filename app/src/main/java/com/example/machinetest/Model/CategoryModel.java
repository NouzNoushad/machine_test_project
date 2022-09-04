package com.example.machinetest.Model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoryModel {
    @SerializedName("title")
    private String categoryTitle;
    @SerializedName("products")
    private Category[] categoryList;
    private boolean isExpandable;

    public CategoryModel(String categoryTitle, Category[] categoryList) {
        this.categoryTitle = categoryTitle;
        this.categoryList = categoryList;
        isExpandable = false;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Category[] getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(Category[] categoryList) {
        this.categoryList = categoryList;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
