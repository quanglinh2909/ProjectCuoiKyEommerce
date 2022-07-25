package com.example.projectcuoikyeommerce.adapter.admin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.admin.DetailOrderActivity;
import com.example.projectcuoikyeommerce.model.Notifycation;
import com.example.projectcuoikyeommerce.presenter.NotificationPresenter;

import java.util.List;

public class NotifycationAdapter extends RecyclerView.Adapter<NotifycationAdapter.ViewHolder>{
    private List<Notifycation> notifycationList;
    public Context mContext;
    private NotificationPresenter notificationPresenter = new NotificationPresenter();

    public NotifycationAdapter(List<Notifycation> notifycationList) {
        this.notifycationList = notifycationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notifycation notifycation = notifycationList.get(position);
        if(notifycation == null) return;
        holder.txtNameProduct.setText(notifycation.getTitle());
        holder.txtPrice.setText(notifycation.getMess());
        holder.boxItem.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailOrderActivity.class);
                 if(notifycation.getStatus() == 0){
                     notificationPresenter.setStatus(notifycation.getId());
                     notifycation.setStatus(1);
                     notificationPresenter.writeNotificationFirebase();
                     holder.boxItem.setBackgroundColor(Color.WHITE);
                 }
                intent.putExtra("idOrder",notifycation.getIdOrder().getId());
                mContext.startActivity(intent);
            }
        });
        if(notifycation.getStatus() == 1){
            holder.boxItem.setBackgroundColor(Color.WHITE);
        }
        if(notifycation.getType() == 0){
            holder.imgProduct.setImageResource(R.drawable.ic_baseline_article_24);
        }
        if(notifycation.getType() == 1){
            holder.imgProduct.setImageResource(R.drawable.ic_baseline_departure_board_24);
        }
        if(notifycation.getType() == 2){
            holder.imgProduct.setImageResource(R.drawable.ic_baseline_clean_hands_24);
        }
        if(notifycation.getType() == 3){
            holder.imgProduct.setImageResource(R.drawable.ic_baseline_free_cancellation_24);
        }


    }

    @Override
    public int getItemCount() {
        if(notifycationList == null) return 0;
        return notifycationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameProduct,txtPrice;
        ImageView imgProduct;
        LinearLayout boxItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            boxItem = itemView.findViewById(R.id.boxItem);
        }
    }
}
