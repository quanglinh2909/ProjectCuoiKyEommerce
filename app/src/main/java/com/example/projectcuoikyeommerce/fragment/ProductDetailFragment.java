package com.example.projectcuoikyeommerce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.category.AdapterDisiption;
import com.example.projectcuoikyeommerce.adapter.product_detail.AdapterAttention;
import com.example.projectcuoikyeommerce.adapter.product_detail.AdapterColor;
import com.example.projectcuoikyeommerce.adapter.product_detail.AdapterImageDetail;
import com.example.projectcuoikyeommerce.adapter.product_detail.AdapterSize;
import com.example.projectcuoikyeommerce.model.Attention;
import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.ColorProduct;
import com.example.projectcuoikyeommerce.model.Description;
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
    private RecyclerView recyclerViewColor,recyclerViewSize,recyclerviewDicription,recyclerviewAttention;
    private AdapterColor adapterColor;
    private AdapterSize adapterSize;
    private AdapterDisiption adapterDisiption;
    private AdapterAttention adapterAttention;
    private List<ColorProduct> colorProductList = new ArrayList<>();
    private List<String> listSize = new ArrayList<>();
    private List<Description> descriptionList = new ArrayList<>();
    private List<Attention> attentionList = new ArrayList<>();

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
        initColors();
        initSize();
        initDescription();
        initAttention();
        return mView;
    }

    private void initAttention() {
        attentionList.add(new Attention(1,"","Do not use bleach"));
        attentionList.add(new Attention(2,"","Do not tumble dry"));
        attentionList.add(new Attention(3,"","Dry clean with tetrachloroethylene"));
        attentionList.add(new Attention(4,"","Iron at a maximum of 110ºC/230ºF"));

        adapterAttention = new AdapterAttention(attentionList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerviewAttention.setLayoutManager(linearLayoutManager);
        recyclerviewAttention.setAdapter(adapterAttention);
    }

    private void initDescription() {
        descriptionList.add(new Description("",null,"",""));
        descriptionList.add(new Description("",null,"",""));


        adapterDisiption = new AdapterDisiption(descriptionList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerviewDicription.setLayoutManager(linearLayoutManager);
        recyclerviewDicription.setAdapter(adapterDisiption);
    }

    private void initSize() {
        listSize.add("S");
        listSize.add("S");
        listSize.add("S");

        adapterSize = new AdapterSize(listSize,null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewSize.setLayoutManager(linearLayoutManager);
        recyclerViewSize.setAdapter(adapterSize);
    }

    private void initColors() {
        colorProductList.add(new ColorProduct("1",null,"",0));
        colorProductList.add(new ColorProduct("1",null,"",0));
        colorProductList.add(new ColorProduct("1",null,"",0));

        adapterColor = new AdapterColor(colorProductList,null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewColor.setLayoutManager(linearLayoutManager);
        recyclerViewColor.setAdapter(adapterColor);

    }

    private void initSlideDetail() {
        adapterImageDetail = new AdapterImageDetail(mView.getContext(),getListBanner());
        viewPagerDetail.setAdapter(adapterImageDetail);
        circleIndicatorDetail.setViewPager(viewPagerDetail);
        adapterImageDetail.registerDataSetObserver(circleIndicatorDetail.getDataSetObserver());
    }
    private List<Image> getListBanner() {
        List<Image> bannerList = new ArrayList<>();
        bannerList.add(new Image(null,R.drawable.item_banner_1,""));
        bannerList.add(new Image(null,R.drawable.item_banner_1,""));

        return bannerList;
    }

    private void initUi() {
        viewPagerDetail = mView.findViewById(R.id.viewPagerDetail);
        circleIndicatorDetail = mView.findViewById(R.id.circleIndicatorDetail);
        recyclerViewColor = mView.findViewById(R.id.recyclerViewColor);
        recyclerViewSize = mView.findViewById(R.id.recyclerViewSize);
        recyclerviewDicription = mView.findViewById(R.id.recyclerviewDicription);
        recyclerviewAttention = mView.findViewById(R.id.recyclerviewAttention);
    }
}