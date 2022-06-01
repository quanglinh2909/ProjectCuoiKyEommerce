package com.example.projectcuoikyeommerce.adapter.product_detail;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.event.ProductDetailEvent;
import com.example.projectcuoikyeommerce.model.Size;

import java.util.List;

public class AdapterSize extends  RecyclerView.Adapter<AdapterSize.ViewHolder>{
    private List<Size> list;
    private ProductDetailEvent productDetailEvent;

    public AdapterSize(List<Size> list,ProductDetailEvent productDetailEvent) {
        this.list = list;
        this.productDetailEvent = productDetailEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_size,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Size size = list.get(position);
       holder.txtSize.setText(size.getName());
       holder.btnSize.setOnClickListener(v -> {
         if(size.isCheck()){
             holder.txtSize.setBackgroundColor(Color.WHITE);
             holder.txtSize.setTextColor(Color.BLACK);
         }else{
             holder.txtSize.setBackgroundColor(Color.BLACK);
             holder.txtSize.setTextColor(Color.WHITE);

         }
         productDetailEvent.clickItemSize(position);
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSize;
        CardView btnSize;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSize = itemView.findViewById(R.id.txtSize);
            btnSize = itemView.findViewById(R.id.btnSize);
        }
    }
}
