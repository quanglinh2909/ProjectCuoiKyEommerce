package com.example.projectcuoikyeommerce.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.MainActivity;
import com.example.projectcuoikyeommerce.adapter.home.AdapterBannerHome;
import com.example.projectcuoikyeommerce.adapter.home.AdapterLogo;
import com.example.projectcuoikyeommerce.adapter.home.AdapterProductHome;
import com.example.projectcuoikyeommerce.adapter.home.AdapterTypeNavigation;
import com.example.projectcuoikyeommerce.event.HomeEvent;
import com.example.projectcuoikyeommerce.event.home.BranchAction;
import com.example.projectcuoikyeommerce.event.home.ProductHomeEvent;
import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.Collection;
import com.example.projectcuoikyeommerce.model.Product;
import com.example.projectcuoikyeommerce.model.TagChild;
import com.example.projectcuoikyeommerce.model.TagParent;
import com.example.projectcuoikyeommerce.presenter.FragmentHomePresenter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment implements BranchAction, HomeEvent {
    private ViewPager viewPagerBanner;
    private CircleIndicator circleIndicatorBanner;
    private AdapterBannerHome adapterBannerHome;
    private View mView;
    private RecyclerView listProduct;
    private RecyclerView listNavigation;
    private AdapterTypeNavigation adapterTypeNavigation;
    private AdapterLogo adapterLogo;
    private RecyclerView listLogo;
    private ProductHomeEvent homeEvent;
    private FragmentHomePresenter fragmentHomePresenter;
    private String TAG = "AAA";

    private List<Banner> bannerList = new ArrayList<>();
    private List<TagParent> navigationList = new ArrayList<>();

    public HomeFragment(ProductHomeEvent homeEvent) {
       this.homeEvent = homeEvent;
    }


    public static HomeFragment newInstance(String param1, String param2, MainActivity mainActivity) {
        HomeFragment fragment = new HomeFragment(mainActivity);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        initUi();
        initBanner();
        initNavigation();
        autoStartBanner();

        return mView;
    }
    private void initUi() {
        viewPagerBanner = mView.findViewById(R.id.viewPagerBanner);
        circleIndicatorBanner = mView.findViewById(R.id.circleIndicatorBanner);
        listProduct=mView.findViewById(R.id.list_item_product);
        listNavigation=mView.findViewById(R.id.type_navigation);
        listLogo=mView.findViewById(R.id.list_logo);
        fragmentHomePresenter = new FragmentHomePresenter();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initNavigation() {
        navigationList = fragmentHomePresenter.getListTagParent();
        adapterTypeNavigation=new AdapterTypeNavigation(navigationList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        listNavigation.setLayoutManager(linearLayoutManager);
        listNavigation.setAdapter(adapterTypeNavigation);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initBanner() {
        bannerList = fragmentHomePresenter.getListBanner();
        adapterBannerHome = new AdapterBannerHome(mView.getContext(),bannerList);
        viewPagerBanner.setAdapter(adapterBannerHome);
        circleIndicatorBanner.setViewPager(viewPagerBanner);
        adapterBannerHome.registerDataSetObserver(circleIndicatorBanner.getDataSetObserver());

        listProduct.setLayoutManager(new GridLayoutManager(mView.getContext(),2));
        listProduct.setAdapter(new AdapterProductHome(initProducts(),homeEvent));



        adapterLogo=new AdapterLogo(initLogo(),mView.getContext(),this);
        listLogo.setLayoutManager(new GridLayoutManager(mView.getContext(),3));
        listLogo.setAdapter(adapterLogo);

    }
    private void autoStartBanner() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(viewPagerBanner.getCurrentItem() == bannerList.size()-1 ){
                    viewPagerBanner.setCurrentItem(0);
                }else {
                    viewPagerBanner.setCurrentItem(viewPagerBanner.getCurrentItem()+1);
                }
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


    public List<String> initLogo(){
        List<String> list=new ArrayList<>();
        list.add("@drawable/boss_logo");
        list.add("@drawable/gucci_logo");
        list.add("@drawable/catier_logo");
        list.add("@drawable/prada_logo");
        list.add("@drawable/tiffany_co_logo");
        list.add("@drawable/burberry_logo");
        return list;
    }
    public List<Product> initProducts(){
        List<Product> list=new ArrayList<>();
        Branch branch=new Branch("br1","New Branch","Branch New Arrival","url");
        Collection collection=new Collection("collect1","bst authumn","mua thu vui ve");
        TagChild tagChild=new TagChild(1,"idParent",null);
        list.add(new Product("pr1",branch,"sp1",43000,43,54,32,11,collection,tagChild));
        list.add(new Product("pr2",branch,"sp2",53000,43,54,32,11,collection,tagChild));
        list.add(new Product("pr3",branch,"sp3",67000,43,54,32,11,collection,tagChild));
        list.add(new Product("pr4",branch,"sp4",400000,43,54,32,11,collection,tagChild));
        return list;
    }

    @Override
    public void clickItemNavigation(int position) {
        listNavigation.setAdapter(adapterTypeNavigation);
    }
}