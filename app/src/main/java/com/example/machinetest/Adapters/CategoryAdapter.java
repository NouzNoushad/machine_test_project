package com.example.machinetest.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.machinetest.Model.Category;
import com.example.machinetest.Model.CategoryModel;
import com.example.machinetest.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<CategoryModel> categoryModelList;
    Category[] arrList = new Category[]{};

    public CategoryAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_nested, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel model = categoryModelList.get(position);
        holder.categoryTitle.setText(model.getCategoryTitle());

        boolean isExpandable = model.isExpandable();
        holder.innerLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        if(isExpandable){
            holder.arrowIconImage.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        }
        else {
            holder.arrowIconImage.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
        }

        // Nested Recycler List
        CategoryItemsAdapter itemsAdapter = new CategoryItemsAdapter(context, arrList);
        holder.innerRecyclerView.setLayoutManager(new GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false));
        holder.innerRecyclerView.setHasFixedSize(true);
        holder.innerRecyclerView.setAdapter(itemsAdapter);

        holder.categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setExpandable(!model.isExpandable());
                arrList = model.getCategoryList();
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout categoryLayout;
        TextView categoryTitle;
        ImageView arrowIconImage;
        RelativeLayout innerLayout;
        RecyclerView innerRecyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryLayout = itemView.findViewById(R.id.categoryLayout);
            categoryTitle = itemView.findViewById(R.id.titleTextView);
            arrowIconImage = itemView.findViewById(R.id.arrowIconImage);
            innerLayout = itemView.findViewById(R.id.innerLayout);
            innerRecyclerView = itemView.findViewById(R.id.innerRecyclerView);
        }
    }
}
