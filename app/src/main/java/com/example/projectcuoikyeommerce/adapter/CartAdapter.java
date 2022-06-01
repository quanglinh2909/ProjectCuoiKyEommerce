package com.example.projectcuoikyeommerce.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.component.FormatPrice;
import com.example.projectcuoikyeommerce.constant.URL;
import com.example.projectcuoikyeommerce.dto.ProductCartDto;
import com.example.projectcuoikyeommerce.event.CartEvent;
import com.example.projectcuoikyeommerce.model.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<ProductCartDto> cartList;
    private CartEvent cartEvent;
    private Context mContext;

    public CartAdapter(List<ProductCartDto> cartList, CartEvent cartEvent) {
        this.cartList = cartList;
        this.cartEvent = cartEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductCartDto productCartDto = cartList.get(position);
        if (productCartDto == null) return;
        holder.txtName.setText(productCartDto.getProductEntityName());
        holder.txtPrice.setText(FormatPrice.formatPrice(productCartDto.getProductEntityPrice()));
        holder.edtSum.setText(productCartDto.getCartEntityQuantity()+"");
        Glide.with(mContext).load(URL.BASE_URL+productCartDto.getImageEntityUrl()).placeholder(R.drawable.banner_item1).dontAnimate().into(holder.imgItemCart);
         if(productCartDto.getCartEntityQuantity() <= 1){
             holder.reduced.setEnabled(false);
         }
        holder.btnIncrease.setOnClickListener(v -> {
            cartEvent.clickIncrease(position);
            holder.reduced.setEnabled(true);
        });
        holder.reduced.setOnClickListener(v -> {
            if(productCartDto.getCartEntityQuantity() > 1){
                cartEvent.clickReduced(position);
            }else{
                holder.reduced.setEnabled(false);
            }
        });
        holder.edtSum.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                if(holder.edtSum.getText().toString().trim().isEmpty()){
                    holder.edtSum.setText(productCartDto.getCartEntityQuantity()+"");
                }else if(Integer.parseInt(holder.edtSum.getText().toString().trim()) <= 0){
                    cartEvent.inputEditTex(position,1);
                }else if(Integer.parseInt(holder.edtSum.getText().toString().trim()) != productCartDto.getCartEntityQuantity()){
                    cartEvent.inputEditTex(position,Integer.parseInt(holder.edtSum.getText().toString().trim()));
                }
            }
        });

        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            cartEvent.selectItem(position,isChecked);
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItemCart;
        TextView txtName,txtPrice;
        CheckBox checkbox;
        ImageButton btnIncrease,reduced;
        EditText edtSum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemCart = itemView.findViewById(R.id.imgItemCart);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            checkbox = itemView.findViewById(R.id.checkbox);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            reduced = itemView.findViewById(R.id.reduced);
            edtSum = itemView.findViewById(R.id.edtSum);
        }
    }
}
