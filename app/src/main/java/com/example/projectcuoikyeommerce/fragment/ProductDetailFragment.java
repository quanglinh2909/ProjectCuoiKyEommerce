package com.example.projectcuoikyeommerce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.AdapterBannerHome;
import com.example.projectcuoikyeommerce.adapter.product_detail.AdapterImageDetail;
import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.Image;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailFragment extends Fragment {


    private static final String TAG = "AAA";
    private ViewPager viewPagerDetail;
    private CircleIndicator circleIndicatorDetail;
    private AdapterImageDetail adapterImageDetail;
    private View mView;
    public ProductDetailFragment() {
        // Required empty public constructor
    }


    public static ProductDetailFragment newInstance(String param1, String param2) {
        ProductDetailFragment fragment = new ProductDetailFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initUi();
        initSlideDetail();
        return mView;
    }

    private void initSlideDetail() {
        adapterImageDetail = new AdapterImageDetail(mView.getContext(),getListBanner());
        viewPagerDetail.setAdapter(adapterImageDetail);
        circleIndicatorDetail.setViewPager(viewPagerDetail);
        adapterImageDetail.registerDataSetObserver(circleIndicatorDetail.getDataSetObserver());
    }
    private List<Image> getListBanner() {
        List<Image> bannerList = new ArrayList<>();
        bannerList.add(new Image(null,R.drawable.item_banner_2,""));
        bannerList.add(new Image(null,R.drawable.item_banner_1,""));
        bannerList.add(new Image(null,R.drawable.item_banner_2,""));
        bannerList.add(new Image(null,R.drawable.item_banner_1,""));

        return bannerList;
    }

    private void initUi() {
        viewPagerDetail = mView.findViewById(R.id.viewPagerDetail);
        circleIndicatorDetail = mView.findViewById(R.id.circleIndicatorDetail);
    }
}