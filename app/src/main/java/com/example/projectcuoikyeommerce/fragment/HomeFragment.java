package com.example.projectcuoikyeommerce.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.MainActivity;
import com.example.projectcuoikyeommerce.adapter.home.AdapterBannerHome;
import com.example.projectcuoikyeommerce.adapter.home.AdapterLogo;
import com.example.projectcuoikyeommerce.adapter.home.AdapterProductHome;
import com.example.projectcuoikyeommerce.adapter.home.AdapterTypeNavigation;
import com.example.projectcuoikyeommerce.component.AnimationRecyclerView;
import com.example.projectcuoikyeommerce.event.ExploreMoreEvent;
import com.example.projectcuoikyeommerce.event.HomeEvent;
import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.TagParent;
import com.example.projectcuoikyeommerce.presenter.FragmentHomePresenter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment implements HomeEvent {
    private ViewPager viewPagerBanner;
    private CircleIndicator circleIndicatorBanner;
    private AdapterBannerHome adapterBannerHome;
    private View mView;
    private RecyclerView recyclerviewProduct;
    private RecyclerView recyclerviewNavigation;
    private RecyclerView recyclerviewLogo;
    private TextView btnExploreMOre;

    private AdapterTypeNavigation adapterTypeNavigation;
    private AdapterProductHome adapterProductHome;
    private AdapterLogo adapterLogo;

    private FragmentHomePresenter fragmentHomePresenter;
    private String TAG = "AAA";

    private List<Banner> bannerList = new ArrayList<>();
    private List<TagParent> navigationList = new ArrayList<>();
    private List<Image> imageList = new ArrayList<>();
    private List<Branch> branchList = new ArrayList<>();
    private ExploreMoreEvent exploreMoreEvent;
    private MainActivity mManMainActivity;

    public HomeFragment(ExploreMoreEvent exploreMoreEvent) {
        this.exploreMoreEvent = exploreMoreEvent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mManMainActivity = (MainActivity) getActivity();
        initUi();
        initBanner();
        initNavigation();
        initProduct();
        initTrademark();
        autoStartBanner();
        handleEvent();

        return mView;
    }

    private void handleEvent() {
        btnExploreMOre.setOnClickListener(v -> {
            exploreMoreEvent.clickExplore();
        });
    }


    private void initUi() {
        viewPagerBanner = mView.findViewById(R.id.viewPagerBanner);
        circleIndicatorBanner = mView.findViewById(R.id.circleIndicatorBanner);
        recyclerviewProduct =mView.findViewById(R.id.list_item_product);
        recyclerviewNavigation =mView.findViewById(R.id.type_navigation);
        recyclerviewLogo =mView.findViewById(R.id.list_logo);
        btnExploreMOre =mView.findViewById(R.id.btnExploreMOre);
        fragmentHomePresenter = new FragmentHomePresenter();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initNavigation() {
        navigationList.addAll(fragmentHomePresenter.getListTagParent());
        adapterTypeNavigation=new AdapterTypeNavigation(navigationList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerviewNavigation.setLayoutManager(linearLayoutManager);
        recyclerviewNavigation.setAdapter(adapterTypeNavigation);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initBanner() {
        bannerList.addAll(fragmentHomePresenter.getListBanner());
        adapterBannerHome = new AdapterBannerHome(mView.getContext(),bannerList);
        viewPagerBanner.setAdapter(adapterBannerHome);
        circleIndicatorBanner.setViewPager(viewPagerBanner);
        adapterBannerHome.registerDataSetObserver(circleIndicatorBanner.getDataSetObserver());

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initProduct() {
        int id = navigationList.size() > 0?navigationList.get(0).getId():-1;
        imageList.addAll(fragmentHomePresenter.getListProductByIdTagParent(id));
        recyclerviewProduct.setLayoutManager(new GridLayoutManager(mView.getContext(),2));
        adapterProductHome = new AdapterProductHome(imageList,null);
        recyclerviewProduct.setAdapter(adapterProductHome);
        if(id > 0){
            mManMainActivity.setTagParent(navigationList.get(0));
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initTrademark() {
        branchList.addAll(fragmentHomePresenter.getListBranch());
        adapterLogo=new AdapterLogo(branchList);
        recyclerviewLogo.setLayoutManager(new GridLayoutManager(mView.getContext(),3));
        recyclerviewLogo.setAdapter(adapterLogo);
    }

    private void autoStartBanner() {
        Handler handler = new Handler();
        Runnable runnable = () -> {
            if(viewPagerBanner.getCurrentItem() == bannerList.size()-1 ){
                viewPagerBanner.setCurrentItem(0);
            }else {
                viewPagerBanner.setCurrentItem(viewPagerBanner.getCurrentItem()+1);
            }
        };
        handler.postDelayed(runnable,3000);
        viewPagerBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void clickItemNavigation(int position) {
        recyclerviewNavigation.setAdapter(adapterTypeNavigation);
        TagParent tagParent = navigationList.get(position);
        imageList.clear();
        AnimationRecyclerView.setAnimation(getContext(),R.anim.layout_animation_dow_to_up,recyclerviewProduct);
        imageList.addAll(fragmentHomePresenter.getListProductByIdTagParent(tagParent.getId()));
        adapterProductHome.notifyDataSetChanged();
        mManMainActivity.setTagParent(tagParent);
    }
}