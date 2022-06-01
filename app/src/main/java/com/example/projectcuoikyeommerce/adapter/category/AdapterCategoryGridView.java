package com.example.projectcuoikyeommerce.adapter.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.component.FormatPrice;
import com.example.projectcuoikyeommerce.constant.URL;
import com.example.projectcuoikyeommerce.event.CateGoryEvent;
import com.example.projectcuoikyeommerce.model.Image;

import java.util.List;

public class AdapterCategoryGridView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CateGoryEvent cateGoryGridViewEvent;
    private List<Image> productList;
    private Context mContext;
    private static  final  int TYPE_ITEM = 1;
    private static  final  int TYPE_LOADING = 2;
    private boolean isLoadDing;


    public AdapterCategoryGridView(CateGoryEvent cateGoryGridViewEvent, List<Image> productList) {
        this.cateGoryGridViewEvent = cateGoryGridViewEvent;
        this.productList = productList;
    }
    @Override
    public int getItemViewType(int position ) {

        if(productList != null && position == productList.size()-1
                && productList.get(productList.size()-1).getProduct() == null){

            return TYPE_LOADING;

        }

        return TYPE_ITEM;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(TYPE_ITEM == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
            mContext = parent.getContext();
            return new ViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
            mContext = parent.getContext();
            return new ViewHolderLoading(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Image image = productList.get(position);
        if (image == null) return;
        if(image.getProduct() != null){
            ViewHolder viewHolder = (ViewHolder) holder;
            Glide.with(mContext).load(URL.BASE_URL+image.getUrl()).placeholder(R.drawable.banner_item1).dontAnimate().into(viewHolder.imageItemProduct);
            viewHolder.txtNameProduct.setText(image.getProduct().getName());
            viewHolder.txtPriceProduct.setText(FormatPrice.formatPrice(image.getProduct().getPrice()));
            viewHolder.itemProduct.setOnClickListener(v -> cateGoryGridViewEvent.clickItemCateGory(position));
        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageItemProduct;
        TextView txtNameProduct,txtPriceProduct;
        LinearLayout itemProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItemProduct = itemView.findViewById(R.id.item_product_image);
            txtNameProduct = itemView.findViewById(R.id.item_product_name);
            txtPriceProduct = itemView.findViewById(R.id.item_product_price);
            itemProduct = itemView.findViewById(R.id.itemProduct);
        }
    }
    public class ViewHolderLoading extends RecyclerView.ViewHolder {

        ProgressBar loading;
        public ViewHolderLoading(@NonNull View itemView) {
            super(itemView);
            loading = itemView.findViewById(R.id.loading);
        }
    }
    public void addFooterLoading(){
        isLoadDing = true;
        productList.add(new Image(3,"",null));
    }
    public void removeFooterLoading(){
        isLoadDing = false;
        int position = productList.size() -1;
        Image image = productList.get(position);
        if(image != null){
            productList.remove(position);
            notifyItemRemoved(position);
        }

    }


}
