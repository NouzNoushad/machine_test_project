package com.example.machinetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.machinetest.databinding.ActivityDetailsBinding;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding detailsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(detailsBinding.getRoot());

        Bundle bundle = getIntent().getExtras();
        String productName = bundle.getString("productName");
        String productPrice = bundle.getString("productPrice");
        String productImage = bundle.getString("productImage");
        String productDescription = bundle.getString("productDescription");


        detailsBinding.productName.setText(productName);
        detailsBinding.productPrice.setText("\u20B9" + productPrice);
        detailsBinding.productDescription.setText(productDescription);
        Picasso.get().load(productImage).into(detailsBinding.productImage);
        detailsBinding.backArrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
    }
}