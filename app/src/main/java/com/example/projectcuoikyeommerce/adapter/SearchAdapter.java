package com.example.projectcuoikyeommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.ProductDetailActivity;
import com.example.projectcuoikyeommerce.activity.admin.EditProductActivity;
import com.example.projectcuoikyeommerce.component.FormatPrice;
import com.example.projectcuoikyeommerce.constant.KeyIntent;
import com.example.projectcuoikyeommerce.constant.URL;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.dto.ProductDto;
import com.example.projectcuoikyeommerce.model.User;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<ProductDto> list;
    private Context mContext;
    private User user = DataLocalManager.getInstance().getUser();

    public SearchAdapter(List<ProductDto> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDto productDto = list.get(position);
        if(productDto == null) return;
        holder.txtNameProduct.setText(productDto.getProductEntityName());
        holder.txtPrice.setText(FormatPrice.formatPrice(productDto.getProductEntityPrice()));
        Glide.with(mContext).load(URL.BASE_URL+productDto.getImageEntityUrl()).placeholder(R.drawable.banner_item1).dontAnimate().into(holder.imgProduct);

        holder.boxItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getRole() == 0){
                    Intent intent = new Intent(mContext,ProductDetailActivity.class);
                    intent.putExtra(KeyIntent.KEY_PRODUCT_ID,productDto.getProductEntityId());
                    mContext.startActivity(intent);
                }else{
                    Intent intent = new Intent(mContext, EditProductActivity.class);
                    intent.putExtra(KeyIntent.KEY_PRODUCT_ID,productDto.getProductEntityId());
                    mContext.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if(list == null) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameProduct,txtPrice;
        ImageView imgProduct;
        LinearLayout boxItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            boxItem = itemView.findViewById(R.id.boxItem);
        }
    }
}
