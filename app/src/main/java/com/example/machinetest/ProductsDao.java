package com.example.machinetest;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.machinetest.Database.Products;

import java.util.List;

@Dao
public interface ProductsDao {

    @Query("SELECT * FROM productsList")
    List<Products> getAllProducts();

    @Insert
    void addProducts(Products products);
}
