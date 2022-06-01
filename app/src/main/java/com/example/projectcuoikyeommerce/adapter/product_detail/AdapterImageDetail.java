package com.example.projectcuoikyeommerce.adapter.product_detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.constant.URL;
import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.Image;

import java.util.List;

public class AdapterImageDetail extends PagerAdapter {
    private Context mContext;
    private List<Image> mListBanners;

    public AdapterImageDetail(Context mContext, List<Image> mListBanners) {
        this.mContext = mContext;
        this.mListBanners = mListBanners;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_slide_image_detail,container,false);
        ImageView imageItem = view.findViewById(R.id.imgItemSlide);
        Image image = mListBanners.get(position);
        if(image != null){
            Glide.with(mContext).load(URL.BASE_URL+image.getUrl()).into(imageItem);
//            Glide.with(view.getContext()).load(URL.BASE_URL+"images/Banner.jpg").placeholder(R.drawable.banner_item1).dontAnimate().into(imageItemBanner);
        }

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(mListBanners != null) return mListBanners.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
