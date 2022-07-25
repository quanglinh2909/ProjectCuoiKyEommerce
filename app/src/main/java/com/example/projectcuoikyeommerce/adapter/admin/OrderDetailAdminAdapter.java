package com.example.projectcuoikyeommerce.adapter.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.admin.DetailOrderActivity;
import com.example.projectcuoikyeommerce.component.FormatPrice;
import com.example.projectcuoikyeommerce.constant.URL;
import com.example.projectcuoikyeommerce.dto.OrderAdminDto;
import com.example.projectcuoikyeommerce.dto.OrderDetailDto;
import com.example.projectcuoikyeommerce.presenter.OrderPresenter;

import java.util.List;

public class OrderDetailAdminAdapter extends RecyclerView.Adapter<OrderDetailAdminAdapter.ViewHolder> {
    private List<OrderDetailDto> oderList;
    private Context mContext;
    public OrderDetailAdminAdapter(List<OrderDetailDto> oderList) {
        this.oderList = oderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail,parent,false);
            mContext = parent.getContext();
            return new ViewHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            OrderDetailDto orderAdminDto = oderList.get(position);
        if(orderAdminDto == null) return;
            Glide.with(mContext).load(URL.BASE_URL+orderAdminDto.getImageEntityUrl()).placeholder(R.drawable.banner_item1).dontAnimate().into(holder.imgProduct);
            holder.txtNameProduct.setText(orderAdminDto.getProductEntityName());
            holder.txtPrice.setText(FormatPrice.formatPrice(orderAdminDto.getOrderDetailEntityPrice()));
            holder.txtTotalPrice.setText(FormatPrice.formatPrice(orderAdminDto.getOrderDetailEntityPrice()
            * orderAdminDto.getOrderDetailEntityQuantity()));
            holder.txtTotalProduct.setText(orderAdminDto.getOrderDetailEntityQuantity()+" sản phẩm");
    }

    @Override
    public int getItemCount() {
        if(oderList == null) return 0;
        return oderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameProduct,txtPrice,txtTotalPrice,txtTotalProduct;
        ImageView imgProduct;
        LinearLayout boxItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtTotalPrice = itemView.findViewById(R.id.txtTotalPrice);
            txtTotalProduct = itemView.findViewById(R.id.txtTotalProduct);
            boxItem = itemView.findViewById(R.id.boxItem);
        }

    }

}
