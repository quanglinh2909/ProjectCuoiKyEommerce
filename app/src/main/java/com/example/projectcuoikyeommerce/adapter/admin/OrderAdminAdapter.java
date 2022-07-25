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
import com.example.projectcuoikyeommerce.adapter.category.AdapterCategoryListView;
import com.example.projectcuoikyeommerce.component.FormatPrice;
import com.example.projectcuoikyeommerce.constant.URL;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.dto.OrderAdminDto;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.Oder;
import com.example.projectcuoikyeommerce.model.User;
import com.example.projectcuoikyeommerce.presenter.OrderPresenter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderAdminAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<OrderAdminDto> oderList;
    private OrderAdminEvent orderAdminEvent;
    private Context mContext;
    private OrderPresenter orderPresenter = new OrderPresenter();
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_LOADING = 2;
    private boolean isLoadDing;
    User user = DataLocalManager.getInstance().getUser();

    public  interface OrderAdminEvent{
        void clickHuy(int position);
        void clickDuyet(int position);
    }

    public OrderAdminAdapter(List<OrderAdminDto> oderList,OrderAdminEvent orderAdminEvent) {
        this.oderList = oderList;
        this.orderAdminEvent = orderAdminEvent;
    }
    @Override
    public int getItemViewType(int position) {
        if (oderList != null && position == oderList.size() - 1 && isLoadDing) {
            return TYPE_LOADING;

        }
        return TYPE_ITEM;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if (TYPE_ITEM == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_admin,parent,false);
            mContext = parent.getContext();
            return new OrderAdminAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            mContext = parent.getContext();
            return new OrderAdminAdapter.ViewHolderLoading(view);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, @SuppressLint("RecyclerView") int position) {
        if (h.getItemViewType() == TYPE_ITEM) {
            ViewHolder holder = (ViewHolder) h;
            OrderAdminDto orderAdminDto = oderList.get(position);
            if(orderAdminDto == null) return;
            if(user.getRole() == 0){
                holder.txtNameClient.setText(orderAdminDto.getOrderDetailEntityIdOrderId().substring(14));
            }else {
                holder.txtNameClient.setText(orderAdminDto.getUserEntityUserName());
            }
            Glide.with(mContext).load(URL.BASE_URL+orderAdminDto.getImageEntityUrl()).placeholder(R.drawable.banner_item1).dontAnimate().into(holder.imgProduct);
            holder.txtNameProduct.setText(orderAdminDto.getProductEntityName());
            holder.txtPrice.setText(FormatPrice.formatPrice(orderAdminDto.getProductEntityPrice()));
            holder.txtTotalPrice.setText(FormatPrice.formatPrice(orderAdminDto.getOrderEntityTotal()));
            holder.txtTotalProduct.setText(orderPresenter.getQuantityProduct(orderAdminDto.getOrderEntityId())+" sản phẩm");
            holder.txtStatus.setText(getTrangThai(orderAdminDto.getOrderEntityStatus()));
            if(orderAdminDto.getOrderEntityStatus() == 3 ){
                holder.btnDuyet.setVisibility(View.GONE);
                holder.line3.setVisibility(View.GONE);

            }
            if( user.getRole() == 0){
                holder.txtStatus.setVisibility(View.GONE);
            }
            if( user.getRole() == 0 && orderAdminDto.getOrderEntityStatus() != 0){
                holder.btnDuyet.setVisibility(View.GONE);
                holder.line3.setVisibility(View.GONE);
            }
            holder.btnDuyet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderAdminEvent.clickHuy(position);
                }
            });
            holder.txtStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderAdminEvent.clickDuyet(position);
                }
            });
            holder.boxItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext.getApplicationContext(), DetailOrderActivity.class);
                    intent.putExtra("idOrder", orderAdminDto.getOrderEntityId());
                    mContext.startActivity(intent);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        if(oderList == null) return 0;
        return oderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameClient,txtNameProduct,txtPrice,txtTotalPrice,txtTotalProduct,txtStatus;
        ImageView imgProduct;
        TextView btnDuyet;
        LinearLayout boxItem;
        View line3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameClient = itemView.findViewById(R.id.txtNameClient);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtTotalPrice = itemView.findViewById(R.id.txtTotalPrice);
            txtTotalProduct = itemView.findViewById(R.id.txtTotalProduct);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            btnDuyet = itemView.findViewById(R.id.btnHuy);
            boxItem = itemView.findViewById(R.id.boxItem);
            line3 = itemView.findViewById(R.id.line3);
        }

    }
    public class ViewHolderLoading extends RecyclerView.ViewHolder {

        ProgressBar loading;

        public ViewHolderLoading(@NonNull View itemView) {
            super(itemView);
            loading = itemView.findViewById(R.id.loading);
        }
    }

    public void addFooterLoading() {
        isLoadDing = true;
        oderList.add(new OrderAdminDto());
    }

    public void removeFooterLoading() {
        isLoadDing = false;
        int position = oderList.size() - 1;
        if(position < 0) return;
        OrderAdminDto image = oderList.get(position);
        if (image != null) {
            oderList.remove(position);
            notifyItemRemoved(position);
        }

    }
    public String getTrangThai(int status) {
        switch (status) {
            case 1:
                return "Giao Hàng";
            case 2:
                return "";
            case 3:
                return "";
            default:
                return "Duyệt";
        }
    }


}
