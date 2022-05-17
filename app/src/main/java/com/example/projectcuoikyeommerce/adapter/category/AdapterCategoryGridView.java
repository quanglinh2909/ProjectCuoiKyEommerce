package com.example.projectcuoikyeommerce.adapter.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.event.CateGoryGridViewEvent;
import com.example.projectcuoikyeommerce.model.Product;

import java.util.List;

public class AdapterCategoryGridView extends RecyclerView.Adapter<AdapterCategoryGridView.ViewHolder> {
    private CateGoryGridViewEvent cateGoryGridViewEvent;
    private List<Product> productList;

    public AdapterCategoryGridView(CateGoryGridViewEvent cateGoryGridViewEvent, List<Product> productList) {
        this.cateGoryGridViewEvent = cateGoryGridViewEvent;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
