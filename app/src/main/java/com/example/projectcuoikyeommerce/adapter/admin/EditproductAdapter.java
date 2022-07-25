package com.example.projectcuoikyeommerce.adapter.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
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
import com.example.projectcuoikyeommerce.model.ImageEdit;

import java.util.List;

public class EditproductAdapter extends RecyclerView.Adapter<EditproductAdapter.ViewHolder> {
    private List<ImageEdit> uriList;
    Context mContext;
    private EventAddProduct eventAddProduct;
    public interface EventAddProduct{
        void clickDelete(int position);
    }
    public EditproductAdapter(List<ImageEdit> uriList, EventAddProduct eventAddProduct) {
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
        ImageEdit imageEdit = uriList.get(position);
        if(imageEdit.getType() == 0){
            Glide.with(mContext).load(URL.BASE_URL+imageEdit.getUrl()).placeholder(R.drawable.banner_item1).dontAnimate().into(holder.imageView);
        }else{
            Glide.with(mContext).load(imageEdit.getUri()).placeholder(R.drawable.banner_item1).dontAnimate().into(holder.imageView);
        }
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
