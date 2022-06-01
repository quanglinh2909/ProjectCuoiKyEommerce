package com.example.projectcuoikyeommerce.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.category.AdapterCategoryGridView;
import com.example.projectcuoikyeommerce.adapter.category.AdapterCategoryListView;
import com.example.projectcuoikyeommerce.component.PaginationScrollListener;
import com.example.projectcuoikyeommerce.constant.KeyIntent;
import com.example.projectcuoikyeommerce.event.CateGoryEvent;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.TagChild;
import com.example.projectcuoikyeommerce.model.TagParent;
import com.example.projectcuoikyeommerce.presenter.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CateGoryEvent {
    private ImageButton btnListViewAndGirdView, btnFilter,btnBack;
    private TextView txtNameParent,txtNameChild,txtTotal;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewCategoryGrid;
    private List<Image> productList = new ArrayList<>();
    private AdapterCategoryGridView adapterCategoryGridView;
    private AdapterCategoryListView adapterCategoryListView;
    private static final int LIST_VIEW = 1;
    private static final int GRID_VIEW = 2;
    private  int viewCurrent = 1;
    private TagParent tagParent;
    private TagChild tagChild;
    private String TAG ="AAA";
    private CategoryPresenter categoryPresenter;

    private boolean isLoading,isLastPage;
    private int totalPage ;
    private int currentPage = 1;
    private int   sum  = 0;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getData();
        initUI();
        initList();
        initGird();
        recyclerViewCategoryGrid.setVisibility(View.GONE);
        handleAction();
        setUI();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        categoryPresenter = new CategoryPresenter();
        if(bundle != null){
            tagParent = (TagParent) bundle.getSerializable(KeyIntent.KEY_TAG_PARENT);
            tagChild = (TagChild) bundle.getSerializable(KeyIntent.KEY_TAF_CHILD);
            productList.addAll( categoryPresenter.getListProduct(tagParent,tagChild,currentPage));
            sum = categoryPresenter.getTotalProduct(tagParent,tagChild);
            totalPage = categoryPresenter.totalPage(sum);

        }
    }

    private void setUI() {
        if(tagParent == null){
            txtNameParent.setVisibility(View.GONE);
        }else{
            txtNameParent.setVisibility(View.VISIBLE);
            txtNameParent.setText(tagParent.getName());

        }
        if(tagChild == null){
            txtNameChild.setVisibility(View.GONE);
        }else{
            txtNameChild.setVisibility(View.VISIBLE);
            txtNameChild.setText(tagChild.getName());

        }
        txtTotal.setText(sum+" Sản phẩm");

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleAction() {
        btnListViewAndGirdView.setOnClickListener(v -> {
            if(viewCurrent == LIST_VIEW){
                viewCurrent = GRID_VIEW;
                recyclerViewCategoryList.setVisibility(View.GONE);
                recyclerViewCategoryGrid.setVisibility(View.VISIBLE);

            }else{
                viewCurrent = LIST_VIEW;
                recyclerViewCategoryList.setVisibility(View.VISIBLE);
                recyclerViewCategoryGrid.setVisibility(View.GONE);
            }
        });
        btnBack.setOnClickListener(v -> finish());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initList() {

        adapterCategoryListView = new AdapterCategoryListView(this,productList);
        if(currentPage < totalPage){
            adapterCategoryListView.addFooterLoading();
        }else{
            isLastPage = true;
        }
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        recyclerViewCategoryList.setAdapter(adapterCategoryListView);

        recyclerViewCategoryList.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItem() {
                isLoading = true;
                currentPage+=1;
                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }
    private void initGird() {
        adapterCategoryGridView = new AdapterCategoryGridView(this, productList);
        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,2);
        recyclerViewCategoryGrid.setLayoutManager(gridLayoutManager);
        recyclerViewCategoryGrid.setAdapter(adapterCategoryGridView);
        btnListViewAndGirdView.setImageResource(R.drawable.ic_listview);

        recyclerViewCategoryGrid.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void loadMoreItem() {
                isLoading = true;
                currentPage+=1;
                loadNextPageGrid();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadNextPage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapterCategoryListView.removeFooterLoading();
                productList.addAll( categoryPresenter.getListProduct(tagParent,tagChild,currentPage));
                adapterCategoryListView.notifyDataSetChanged();
                isLoading =false;
                if(currentPage < totalPage){
                    adapterCategoryListView.addFooterLoading();
                }else{
                    isLastPage = true;
                }
            }
        },1000);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadNextPageGrid() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapterCategoryGridView.removeFooterLoading();
                productList.addAll( categoryPresenter.getListProduct(tagParent,tagChild,currentPage));
                adapterCategoryGridView.notifyDataSetChanged();
                isLoading =false;
                if(currentPage < totalPage){
                    adapterCategoryGridView.addFooterLoading();
                }else{
                    isLastPage = true;
                }
            }
        },1000);

    }
    

    private void initUI() {
        btnListViewAndGirdView = findViewById(R.id.btnListViewAndGirdView);
        btnFilter = findViewById(R.id.btnFilter);
        recyclerViewCategoryList = findViewById(R.id.recyclerViewCategory);
        recyclerViewCategoryGrid = findViewById(R.id.recyclerViewCategoryGrid);
        txtNameParent = findViewById(R.id.txtNameParent);
        txtNameChild = findViewById(R.id.txtNameChild);
        txtTotal = findViewById(R.id.txtTotal);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    public void clickItemCateGory(int position) {
        Intent intent = new Intent(CategoryActivity.this,ProductDetailActivity.class);
        intent.putExtra(KeyIntent.KEY_PRODUCT_ID,productList.get(position).getProduct().getId());
        startActivity(intent);
    }
}