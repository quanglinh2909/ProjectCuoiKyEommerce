package com.example.projectcuoikyeommerce.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.ProductDetailActivity;
import com.example.projectcuoikyeommerce.component.FormatPrice;
import com.example.projectcuoikyeommerce.constant.KeyIntent;
import com.example.projectcuoikyeommerce.constant.URL;
import com.example.projectcuoikyeommerce.event.HomeEvent;
import com.example.projectcuoikyeommerce.model.Image;

import java.util.List;

public class AdapterProductHome extends RecyclerView.Adapter<AdapterProductHome.ViewHolderProduct> {
    private List<Image> imageList;
    private HomeEvent homeEvent;
    private Context mContext;

    public AdapterProductHome(List<Image> imageList, HomeEvent homeEvent) {
        this.imageList = imageList;
        this.homeEvent = homeEvent;
    }

    @NonNull
    @Override
    public ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        mContext = parent.getContext();
        return new ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduct holder, int position) {
        Image image= imageList.get(position);
        holder.titleProductView.setText(image.getProduct().getName());
        holder.priceProductView.setText(FormatPrice.formatPrice(image.getProduct().getPrice()));

        Glide.with(mContext).load(URL.BASE_URL+image.getUrl()).placeholder(R.drawable.banner_item1).dontAnimate().into(holder.imageProductView);
        holder.imageProductView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext.getApplicationContext(), ProductDetailActivity.class);
            intent.putExtra(KeyIntent.KEY_PRODUCT_ID,image.getProduct().getId());
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
    public class ViewHolderProduct extends RecyclerView.ViewHolder{
        ImageView imageProductView;
        TextView titleProductView;
//        TextView branchProductView;
        TextView priceProductView;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            imageProductView=itemView.findViewById(R.id.item_product_image);
            titleProductView=itemView.findViewById(R.id.item_product_name);
//        branchProductView=itemView.findViewById(R.id.item_product_branch);
            priceProductView=itemView.findViewById(R.id.item_product_price);

        }

    }

}
