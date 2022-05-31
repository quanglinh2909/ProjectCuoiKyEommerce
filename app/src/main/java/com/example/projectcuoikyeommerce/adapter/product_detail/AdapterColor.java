package com.example.projectcuoikyeommerce.adapter.product_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.model.ColorProduct;

import java.util.List;
import com.example.projectcuoikyeommerce.R;


public class AdapterColor extends RecyclerView.Adapter<AdapterColor.ViewHolder> {
    private List<ColorProduct> colorProductList;

    public AdapterColor(List<ColorProduct> colorProductList) {
        this.colorProductList = colorProductList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color_detail,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return colorProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
