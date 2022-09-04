package com.example.machinetest.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.machinetest.Database.DatabaseHelper;
import com.example.machinetest.Database.Products;
import com.example.machinetest.DetailsActivity;
import com.example.machinetest.Model.Category;
import com.example.machinetest.R;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;


public class CategoryItemsAdapter extends RecyclerView.Adapter<CategoryItemsAdapter.ItemsViewHolder> {

    Context context;
    Category[] itemArrList;

    public CategoryItemsAdapter(Context context, Category[] itemArrList) {
        this.context = context;
        this.itemArrList = itemArrList;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_grid_card, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("position", String.valueOf(position));
        Category model = Arrays.asList(itemArrList).get(position);
        String title = model.getTitle();
        String price = model.getPrice();
        String imageUrl = model.getImageUrl();
        String description = model.getDescription();

        // Database
        DatabaseHelper databaseHelper = DatabaseHelper.getDatabase(context);

        databaseHelper.productsDao().addProducts(
                new Products(title, price, imageUrl, description)
        );
        List<Products> arrProducts = databaseHelper.productsDao().getAllProducts();
        for(Products arrProduct : arrProducts){
            Log.d("SubTitle", String.valueOf(arrProduct.getTitle().length()));
            holder.categoryName.setText(arrProduct.getTitle());
            holder.categoryPrice.setText("\u20B9" + arrProduct.getPrice());
            Picasso.get().load(arrProduct.getImageUrl()).into(holder.categoryImage);

            holder.categoryLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detailsIntent = new Intent(context, DetailsActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("productName", arrProduct.getTitle());
                    bundle.putString("productPrice", arrProduct.getPrice());
                    bundle.putString("productImage", arrProduct.getImageUrl());
                    bundle.putString("productDescription", arrProduct.getDescription());
                    detailsIntent.putExtras(bundle);
                    detailsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(detailsIntent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(itemArrList == null){
            return 0;
        }
        return itemArrList.length;
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        LinearLayout categoryLinearLayout;
        ImageView categoryImage;
        TextView categoryName, categoryPrice;
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryLinearLayout = itemView.findViewById(R.id.categoryLinearLayout);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPrice = itemView.findViewById(R.id.categoryPrice);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            Log.d("list", String.valueOf(categoryName));
        }
    }
}
