package com.example.projectcuoikyeommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.AdapterBannerHome;
import com.example.projectcuoikyeommerce.model.Banner;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPagerBanner;
    private CircleIndicator circleIndicatorBanner;
    private AdapterBannerHome adapterBannerHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initBanner();
    }

    private void initBanner() {
        adapterBannerHome = new AdapterBannerHome(this,getListBanner());
        viewPagerBanner.setAdapter(adapterBannerHome);
        circleIndicatorBanner.setViewPager(viewPagerBanner);
        adapterBannerHome.registerDataSetObserver(circleIndicatorBanner.getDataSetObserver());
    }

    private List<Banner> getListBanner() {
        List<Banner> bannerList = new ArrayList<>();
        bannerList.add(new Banner("",""));
        bannerList.add(new Banner("",""));
        bannerList.add(new Banner("",""));
        bannerList.add(new Banner("",""));
        return bannerList;
    }

    private void init() {
        viewPagerBanner = findViewById(R.id.viewPagerBanner);
        circleIndicatorBanner = findViewById(R.id.circleIndicatorBanner);
    }
}