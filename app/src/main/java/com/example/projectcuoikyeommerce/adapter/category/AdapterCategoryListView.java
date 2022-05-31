package com.example.projectcuoikyeommerce.adapter.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

public class AdapterCategoryListView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CateGoryEvent cateGoryGridViewEvent;
    private List<Image> productList;
    private Context mContext;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_LOADING = 2;
    private boolean isLoadDing;

    public AdapterCategoryListView(CateGoryEvent cateGoryGridViewEvent, List<Image> productList) {
        this.cateGoryGridViewEvent = cateGoryGridViewEvent;
        this.productList = productList;
    }

    @Override
    public int getItemViewType(int position) {
        if (productList != null && position == productList.size() - 1 && isLoadDing) {
            return TYPE_LOADING;

        }
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (TYPE_ITEM == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list_view, parent, false);
            mContext = parent.getContext();
            return new ViewHolderProduct(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            mContext = parent.getContext();
            return new ViewHolderLoading(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM) {
            ViewHolderProduct viewHolderProduct = (ViewHolderProduct) holder;
            Image image = productList.get(position);
            Glide.with(mContext).load(URL.BASE_URL + image.getUrl()).placeholder(R.drawable.banner_item1).dontAnimate().into(viewHolderProduct.imageItemProduct);
            viewHolderProduct.txtNameProduct.setText(image.getProduct().getName());
            viewHolderProduct.txtPriceProduct.setText(FormatPrice.formatPrice(image.getProduct().getPrice()));
            viewHolderProduct.itemProduct.setOnClickListener(v -> cateGoryGridViewEvent.clickItemCateGory(position));

            if (image.getProduct().getS() > 0) {
                viewHolderProduct.txtS.setVisibility(View.VISIBLE);
            } else {
                viewHolderProduct.txtS.setVisibility(View.GONE);

            }
            if (image.getProduct().getL() > 0) {
                viewHolderProduct.txtL.setVisibility(View.VISIBLE);
            } else {
                viewHolderProduct.txtL.setVisibility(View.GONE);

            }
            if (image.getProduct().getM() > 0) {
                viewHolderProduct.txtM.setVisibility(View.VISIBLE);
            } else {
                viewHolderProduct.txtM.setVisibility(View.GONE);

            }
            if (image.getProduct().getXl() > 0) {
                viewHolderProduct.txtXL.setVisibility(View.VISIBLE);
            } else {
                viewHolderProduct.txtXL.setVisibility(View.GONE);

            }

        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {
        ImageView imageItemProduct;
        TextView txtNameProduct, txtPriceProduct, txtS, txtM, txtL, txtXL;
        RelativeLayout itemProduct;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            imageItemProduct = itemView.findViewById(R.id.imageItemProduct);
            txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
            txtPriceProduct = itemView.findViewById(R.id.txtPriceProduct);
            txtS = itemView.findViewById(R.id.txtS);
            txtM = itemView.findViewById(R.id.txtM);
            txtL = itemView.findViewById(R.id.txtL);
            txtXL = itemView.findViewById(R.id.txtXL);
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

    public void addFooterLoading() {
        isLoadDing = true;
        productList.add(new Image(3, "", null));
    }

    public void removeFooterLoading() {
        isLoadDing = false;
        int position = productList.size() - 1;
        Image image = productList.get(position);
        if (image != null) {
            productList.remove(position);
            notifyItemRemoved(position);
        }

    }

}
