package com.example.projectcuoikyeommerce.adapter.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.constant.URL;

import java.util.List;

public class AddproductAdapter extends RecyclerView.Adapter<AddproductAdapter.ViewHolder> {
    private List<Uri> uriList;
    Context mContext;
    private EventAddProduct eventAddProduct;
    public interface EventAddProduct{
        void clickDelete(int position);
    }
    public AddproductAdapter(List<Uri> uriList,EventAddProduct eventAddProduct) {
        this.uriList = uriList;
        this.eventAddProduct = eventAddProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custome_image,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(mContext).load(uriList.get(position)).placeholder(R.drawable.banner_item1).dontAnimate().into(holder.imageView);
        holder.btnDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventAddProduct.clickDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageButton btnDeleteImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageItemProduct);
            btnDeleteImage = itemView.findViewById(R.id.btnDeleteImage);
        }
    }
}
