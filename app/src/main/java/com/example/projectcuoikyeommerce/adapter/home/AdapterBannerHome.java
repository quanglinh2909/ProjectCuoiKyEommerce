package com.example.projectcuoikyeommerce.adapter.home;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.constant.URL;
import com.example.projectcuoikyeommerce.model.Banner;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterBannerHome extends PagerAdapter {
    private Context mContext;
    private List<Banner> mListBanners;

    public AdapterBannerHome(Context mContext, List<Banner> mListBanners) {
        this.mContext = mContext;
        this.mListBanners = mListBanners;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_banner_home,container,false);
        ImageView imageItemBanner = view.findViewById(R.id.imgItemBanner);
        Banner banner = mListBanners.get(position);
        if(banner != null){
//            Glide.with(mContext).load(Uri.parse(URL.BASE_URL+"images/Banner.jpg")).into(imageItemBanner);
            Glide.with(view.getContext()).load(URL.BASE_URL+"images/Banner.jpg").placeholder(R.drawable.banner_item1).dontAnimate().into(imageItemBanner);

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
