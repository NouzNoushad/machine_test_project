package com.example.machinetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.machinetest.Adapters.CategoryAdapter;
import com.example.machinetest.Model.CategoriesModel;
import com.example.machinetest.ViewModel.ProductsViewModel;
import com.example.machinetest.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ProductsViewModel productsViewModel;
    public static ArrayList outerArrList = new ArrayList<>();
    private CategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productsViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        productsViewModel.getProductsList().observe(this, new Observer<CategoriesModel>() {
            @Override
            public void onChanged(CategoriesModel categoriesModel) {
                assert categoriesModel != null;
                outerArrList = new ArrayList<>(Arrays.asList(categoriesModel.getCategories()));
                adapter = new CategoryAdapter(getApplicationContext(), outerArrList);
                binding.recyclerView.setAdapter(adapter);
            }
        });
        productsViewModel.fetchProductsData();
    }
}