package com.example.projectcuoikyeommerce.fragment.admin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.CategoryActivity;
import com.example.projectcuoikyeommerce.activity.ProductDetailActivity;
import com.example.projectcuoikyeommerce.activity.admin.EditProductActivity;
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


public class ProductAdminFragment extends Fragment implements CateGoryEvent {
    private ImageButton btnListViewAndGirdView, btnFilter;
    private TextView txtNameParent, txtNameChild, txtTotal;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewCategoryGrid;
    private List<Image> productList = new ArrayList<>();
    private AdapterCategoryGridView adapterCategoryGridView;
    private AdapterCategoryListView adapterCategoryListView;
    private static final int LIST_VIEW = 1;
    private static final int GRID_VIEW = 2;
    private int viewCurrent = 1;
    private String TAG = "AAA";
    private CategoryPresenter categoryPresenter = new CategoryPresenter();

    private boolean isLoading, isLastPage;
    private int totalPage;
    private int currentPage = 1;
    private int sum = 0;
    private View view;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_admin, container, false);
        initUI();
        getData();
        initList();
        initGird();
        recyclerViewCategoryGrid.setVisibility(View.GONE);
        handleAction();
        setUI();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getData() {

        productList.addAll(categoryPresenter.getAllProduct(currentPage, 20));
        sum = categoryPresenter.getSumProduct();
        Log.d(TAG, "getData: " + sum);
        totalPage = categoryPresenter.totalPage(sum);


    }

    private void setUI() {

        txtTotal.setText(sum + " Sản phẩm");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleAction() {
        btnListViewAndGirdView.setOnClickListener(v -> {
            if (viewCurrent == LIST_VIEW) {
                viewCurrent = GRID_VIEW;
                recyclerViewCategoryList.setVisibility(View.GONE);
                recyclerViewCategoryGrid.setVisibility(View.VISIBLE);

            } else {
                viewCurrent = LIST_VIEW;
                recyclerViewCategoryList.setVisibility(View.VISIBLE);
                recyclerViewCategoryGrid.setVisibility(View.GONE);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initList() {

        adapterCategoryListView = new AdapterCategoryListView(this, productList);
        if (currentPage < totalPage) {
            adapterCategoryListView.addFooterLoading();
        } else {
            isLastPage = true;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        recyclerViewCategoryList.setAdapter(adapterCategoryListView);

        recyclerViewCategoryList.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItem() {
                isLoading = true;
                currentPage += 1;
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewCategoryGrid.setLayoutManager(gridLayoutManager);
        recyclerViewCategoryGrid.setAdapter(adapterCategoryGridView);
        btnListViewAndGirdView.setImageResource(R.drawable.ic_listview);

        recyclerViewCategoryGrid.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void loadMoreItem() {
                isLoading = true;
                currentPage += 1;
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
                productList.addAll(categoryPresenter.getAllProduct(currentPage,20));
                adapterCategoryListView.notifyDataSetChanged();
                isLoading = false;
                if (currentPage < totalPage) {
                    adapterCategoryListView.addFooterLoading();
                } else {
                    isLastPage = true;
                }
            }
        }, 1000);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadNextPageGrid() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapterCategoryGridView.removeFooterLoading();
                productList.addAll(categoryPresenter.getAllProduct(currentPage,20));
                adapterCategoryGridView.notifyDataSetChanged();
                isLoading = false;
                if (currentPage < totalPage) {
                    adapterCategoryGridView.addFooterLoading();
                } else {
                    isLastPage = true;
                }
            }
        }, 1000);

    }


    private void initUI() {
        btnListViewAndGirdView = view.findViewById(R.id.btnListViewAndGirdView);
        btnFilter = view.findViewById(R.id.btnFilter);
        recyclerViewCategoryList = view.findViewById(R.id.recyclerViewCategory);
        recyclerViewCategoryGrid = view.findViewById(R.id.recyclerViewCategoryGrid);
        txtNameParent = view.findViewById(R.id.txtNameParent);
        txtNameChild = view.findViewById(R.id.txtNameChild);
        txtTotal = view.findViewById(R.id.txtTotal);
    }

    @Override
    public void clickItemCateGory(int position) {
        Intent intent = new Intent(getContext(), EditProductActivity.class);
        intent.putExtra(KeyIntent.KEY_PRODUCT_ID, productList.get(position).getProduct().getId());
        startActivity(intent);
    }
}