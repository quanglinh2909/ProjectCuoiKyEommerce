package com.example.projectcuoikyeommerce.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.product_detail.AdapterDisiption;
import com.example.projectcuoikyeommerce.adapter.product_detail.AdapterAttention;
import com.example.projectcuoikyeommerce.adapter.product_detail.AdapterColor;
import com.example.projectcuoikyeommerce.adapter.product_detail.AdapterImageDetail;
import com.example.projectcuoikyeommerce.adapter.product_detail.AdapterSize;
import com.example.projectcuoikyeommerce.component.FormatPrice;
import com.example.projectcuoikyeommerce.constant.KeyIntent;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.dto.CartDto;
import com.example.projectcuoikyeommerce.event.ProductDetailEvent;
import com.example.projectcuoikyeommerce.model.Attention;
import com.example.projectcuoikyeommerce.model.Cart;
import com.example.projectcuoikyeommerce.model.ColorProduct;
import com.example.projectcuoikyeommerce.model.Description;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.Product;
import com.example.projectcuoikyeommerce.model.Size;
import com.example.projectcuoikyeommerce.model.User;
import com.example.projectcuoikyeommerce.presenter.ProductDetailPresenter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailEvent {
    private static final String TAG = "AAA";
    private ViewPager viewPagerDetail;
    private ImageButton btnAddCart;
    private CardView btnBack;
    private CircleIndicator circleIndicatorDetail;
    private AdapterImageDetail adapterImageDetail;
    private RecyclerView recyclerViewSize,recyclerviewDicription,recyclerviewAttention;
    private TextView txtNameProduct,txtPriceProduct;
    private AdapterColor adapterColor;
    private AdapterSize adapterSize;
    private AdapterDisiption adapterDisiption;
    private AdapterAttention adapterAttention;

    private List<ColorProduct> colorProductList = new ArrayList<>();
    private List<Size> listSize = new ArrayList<>();
    private List<Description> descriptionList = new ArrayList<>();
    private List<Attention> attentionList = new ArrayList<>();
    private List<Image> imageList = new ArrayList<>();
    private String idProduct = "";
    private Product product;

    private ProductDetailPresenter presenter;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initUi();
        getData();
        initSlideDetail();
//        initColors();
        initSize();
        initDescription();
        initAttention();
        handleAction();
        autoStartBanner();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleAction() {
        btnBack.setOnClickListener(v -> finish());
        btnAddCart.setOnClickListener(v -> {
          User user = DataLocalManager.getInstance().getUser();
          boolean check = false;
            for ( Size size: listSize) {
                if(size.isCheck()){
                    CartDto cart = new CartDto(user.getId(),idProduct,1, size.getName());
                    presenter.insertCard(cart) ;
                    check  = true;
                }

            }
            if(check){
                Toast.makeText(this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Vui long chon size", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getData() {
        Intent intent = getIntent();
        if(intent.hasExtra(KeyIntent.KEY_PRODUCT_ID)){
            idProduct = intent.getStringExtra(KeyIntent.KEY_PRODUCT_ID);
            product = presenter.getProductById(idProduct);
            txtNameProduct.setText(product.getName());
            txtPriceProduct.setText(FormatPrice.formatPrice(product.getPrice()));
        }
    }

    private void initAttention() {
        attentionList.add(new Attention(1,"","Do not use bleach"));
        attentionList.add(new Attention(2,"","Do not tumble dry"));
        attentionList.add(new Attention(3,"","Dry clean with tetrachloroethylene"));
        attentionList.add(new Attention(4,"","Iron at a maximum of 110ºC/230ºF"));

        adapterAttention = new AdapterAttention(attentionList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerviewAttention.setLayoutManager(linearLayoutManager);
        recyclerviewAttention.setAdapter(adapterAttention);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initDescription() {
       descriptionList.addAll(presenter.getDescription(idProduct));
        adapterDisiption = new AdapterDisiption(descriptionList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerviewDicription.setLayoutManager(linearLayoutManager);
        recyclerviewDicription.setAdapter(adapterDisiption);
    }

    private void initSize() {
       listSize.addAll(presenter.getListSize(product));
        adapterSize = new AdapterSize(listSize,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewSize.setLayoutManager(linearLayoutManager);
        recyclerViewSize.setAdapter(adapterSize);
    }

//    private void initColors() {
//        colorProductList.add(new ColorProduct("1",null,"",0));
//        colorProductList.add(new ColorProduct("1",null,"",0));
//        colorProductList.add(new ColorProduct("1",null,"",0));
//
//        adapterColor = new AdapterColor(colorProductList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
////        recyclerViewColor.setLayoutManager(linearLayoutManager);
////        recyclerViewColor.setAdapter(adapterColor);
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initSlideDetail() {
        imageList = presenter.getListImage(idProduct);
        adapterImageDetail = new AdapterImageDetail(this,imageList);
        viewPagerDetail.setAdapter(adapterImageDetail);
        circleIndicatorDetail.setViewPager(viewPagerDetail);
        adapterImageDetail.registerDataSetObserver(circleIndicatorDetail.getDataSetObserver());
    }


    private void initUi() {
        viewPagerDetail =findViewById(R.id.viewPagerDetail);
        txtPriceProduct =findViewById(R.id.txtPriceProduct);
        txtNameProduct =findViewById(R.id.txtNameProduct);
        circleIndicatorDetail =findViewById(R.id.circleIndicatorDetail);
//        recyclerViewColor = findViewById(R.id.recyclerViewColor);
        recyclerViewSize = findViewById(R.id.recyclerViewSize);
        recyclerviewDicription = findViewById(R.id.recyclerviewDicription);
        recyclerviewAttention = findViewById(R.id.recyclerviewAttention);
        btnAddCart = findViewById(R.id.btnAddCart);
        btnBack = findViewById(R.id.btnBack);
        presenter= new ProductDetailPresenter();

    }
    private void autoStartBanner() {
        Handler handler = new Handler();
        Runnable runnable = () -> {
            if(viewPagerDetail.getCurrentItem() == imageList.size()-1 ){
                viewPagerDetail.setCurrentItem(0);
            }else {
                viewPagerDetail.setCurrentItem(viewPagerDetail.getCurrentItem()+1);
            }
        };
        handler.postDelayed(runnable,3000);
        viewPagerDetail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    @Override
    public void clickItemSize(int position) {
        Size size = listSize.get(position);
        if(size.isCheck()){
            listSize.get(position).setCheck(false);
        }else {
            listSize.get(position).setCheck(true);

        }

    }
}