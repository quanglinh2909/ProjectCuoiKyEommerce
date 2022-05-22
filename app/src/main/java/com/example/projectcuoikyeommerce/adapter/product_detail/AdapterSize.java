package com.example.projectcuoikyeommerce.adapter.product_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.event.detail.DetailEvent;

import java.util.List;

public class AdapterSize extends  RecyclerView.Adapter<AdapterSize.ViewHolder>{
    private List<String> list;
    private DetailEvent detailEvent;

    public AdapterSize(List<String> list, DetailEvent detailEvent) {
        this.list = list;
        this.detailEvent = detailEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_size,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
