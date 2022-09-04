package com.example.machinetest.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.machinetest.CategoryInterface;

import com.example.machinetest.Model.CategoriesModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsViewModel extends ViewModel {
    private MutableLiveData<CategoriesModel> productsList;

    public ProductsViewModel() {
        productsList = new MutableLiveData<>();
    }

    public MutableLiveData<CategoriesModel> getProductsList() {
        return productsList;
    }

    public void fetchProductsData (){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryInterface categoryInterface = retrofit.create(CategoryInterface.class);
        Call<CategoriesModel> call = categoryInterface.getCategories();
        call.enqueue(new Callback<CategoriesModel>() {
            @Override
            public void onResponse(Call<CategoriesModel> call, Response<CategoriesModel> response) {
                CategoriesModel categoriesResponse = response.body();
                productsList.postValue(categoriesResponse);
            }

            @Override
            public void onFailure(Call<CategoriesModel> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}
