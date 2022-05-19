package com.example.projectcuoikyeommerce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.home.AdapterBannerHome;
import com.example.projectcuoikyeommerce.adapter.home.AdapterLogo;
import com.example.projectcuoikyeommerce.adapter.home.AdapterProductHome;
import com.example.projectcuoikyeommerce.adapter.home.AdapterTypeNavigation;
import com.example.projectcuoikyeommerce.component.ItemNavigation;
import com.example.projectcuoikyeommerce.event.home.BranchAction;
import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.Collection;
import com.example.projectcuoikyeommerce.model.Product;
import com.example.projectcuoikyeommerce.model.TagChild;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment implements BranchAction {
    private ViewPager viewPagerBanner;
    private CircleIndicator circleIndicatorBanner;
    private AdapterBannerHome adapterBannerHome;
    private View mView;
    private RecyclerView listProduct;
    private RecyclerView listNavigation;
    private AdapterTypeNavigation adapterTypeNavigation;
    private AdapterLogo adapterLogo;
    private RecyclerView listLogo;

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
        listNavigation=mView.findViewById(R.id.type_navigation);
        listLogo=mView.findViewById(R.id.list_logo);
    }
    private void initBanner() {
        adapterBannerHome = new AdapterBannerHome(mView.getContext(),getListBanner());
        viewPagerBanner.setAdapter(adapterBannerHome);
        circleIndicatorBanner.setViewPager(viewPagerBanner);
        adapterBannerHome.registerDataSetObserver(circleIndicatorBanner.getDataSetObserver());
        listProduct.setLayoutManager(new GridLayoutManager(mView.getContext(),2));
        listProduct.setAdapter(new AdapterProductHome(initProducts()));
        adapterTypeNavigation=new AdapterTypeNavigation(initNavigation());
        listNavigation.setLayoutManager(new GridLayoutManager(mView.getContext(),adapterTypeNavigation.getItemCount()));
        listNavigation.setAdapter(adapterTypeNavigation);
        adapterLogo=new AdapterLogo(initLogo(),mView.getContext(),this);
        listLogo.setLayoutManager(new GridLayoutManager(mView.getContext(),3));
        listLogo.setAdapter(adapterLogo);

    }
    public List<ItemNavigation> initNavigation(){
        List<ItemNavigation> list=new ArrayList<>();
        list.add(new ItemNavigation("All",true));
        list.add(new ItemNavigation("Apparel",false));
        list.add(new ItemNavigation("Dress",false));
        list.add(new ItemNavigation("TShirt",false));
        list.add(new ItemNavigation("Bag",false));
        return list;
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
    private List<Banner> getListBanner() {
        List<Banner> bannerList = new ArrayList<>();
        bannerList.add(new Banner("",""));
        bannerList.add(new Banner("",""));
        bannerList.add(new Banner("",""));
        bannerList.add(new Banner("",""));
        return bannerList;
    }
}