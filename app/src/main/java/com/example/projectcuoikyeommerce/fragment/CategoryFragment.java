package com.example.projectcuoikyeommerce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.category.AdapterCategoryGridView;
import com.example.projectcuoikyeommerce.adapter.category.AdapterCategoryListView;
import com.example.projectcuoikyeommerce.event.CateGoryGridViewEvent;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.Collection;
import com.example.projectcuoikyeommerce.model.Product;
import com.example.projectcuoikyeommerce.model.TagChild;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment implements CateGoryGridViewEvent {

    private View mView;
    private ImageButton btnListViewAndGirdView, btnFilter;
    private RecyclerView recyclerViewCategory;
    private List<Product> productList = new ArrayList<>();
    private AdapterCategoryGridView adapterCategory;
    private AdapterCategoryListView adapterCategoryListView;
    private static final int LIST_VIEW = 1;
    private static final int GRID_VIEW = 2;
    private  int viewCurrent = 1;

    public CategoryFragment() {
    }


    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        mView = inflater.inflate(R.layout.fragment_category, container, false);
        initUI();
        initList();
        handleAction();
        return mView;
    }

    private void handleAction() {
        btnListViewAndGirdView.setOnClickListener(v -> {
          if(viewCurrent == LIST_VIEW){
              viewCurrent = GRID_VIEW;
              adapterCategory = new AdapterCategoryGridView(this, productList);
              recyclerViewCategory.setLayoutManager(new GridLayoutManager(mView.getContext(),2));
              recyclerViewCategory.setAdapter(adapterCategory);
              btnListViewAndGirdView.setImageResource(R.drawable.ic_listview);


          }else{
              viewCurrent = LIST_VIEW;
              LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
              linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
              recyclerViewCategory.setLayoutManager(linearLayoutManager);
              recyclerViewCategory.setAdapter(adapterCategoryListView);
              btnListViewAndGirdView.setImageResource(R.drawable.ic_grid_view);

          }
        });
    }

    private void initList() {
        productList.add(new Product("pr1", null, "sp1", 43000, 43, 54, 32, 11, null, null));
        productList.add(new Product("pr1", null, "sp1", 43000, 43, 54, 32, 11, null, null));
        productList.add(new Product("pr1", null, "sp1", 43000, 43, 54, 32, 11, null, null));
        productList.add(new Product("pr1", null, "sp1", 43000, 43, 54, 32, 11, null, null));
        productList.add(new Product("pr1", null, "sp1", 43000, 43, 54, 32, 11, null, null));
        productList.add(new Product("pr1", null, "sp1", 43000, 43, 54, 32, 11, null, null));
        productList.add(new Product("pr1", null, "sp1", 43000, 43, 54, 32, 11, null, null));
        productList.add(new Product("pr1", null, "sp1", 43000, 43, 54, 32, 11, null, null));

        adapterCategoryListView = new AdapterCategoryListView(this,productList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewCategory.setLayoutManager(linearLayoutManager);
        recyclerViewCategory.setAdapter(adapterCategoryListView);

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

    private void initUI() {
        btnListViewAndGirdView = mView.findViewById(R.id.btnListViewAndGirdView);
        btnFilter = mView.findViewById(R.id.btnFilter);
        recyclerViewCategory = mView.findViewById(R.id.recyclerViewCategory);
    }
}