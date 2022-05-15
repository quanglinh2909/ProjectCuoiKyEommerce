package com.example.projectcuoikyeommerce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.AdapterBannerHome;
import com.example.projectcuoikyeommerce.model.Banner;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {
    private ViewPager viewPagerBanner;
    private CircleIndicator circleIndicatorBanner;
    private AdapterBannerHome adapterBannerHome;
    private View mView;
    private RecyclerView listProduct;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        initUi();
        initBanner();
        return mView;
    }
    private void initUi() {
        viewPagerBanner = mView.findViewById(R.id.viewPagerBanner);
        circleIndicatorBanner = mView.findViewById(R.id.circleIndicatorBanner);
        listProduct=mView.findViewById(R.id.list_item_product);
    }
    private void initBanner() {
        adapterBannerHome = new AdapterBannerHome(mView.getContext(),getListBanner());
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
}