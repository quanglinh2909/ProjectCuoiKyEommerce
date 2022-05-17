package com.example.projectcuoikyeommerce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.projectcuoikyeommerce.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

   private View mView;
   private ImageButton btnListViewAndGirdView,btnFilter;
   private RecyclerView recyclerViewCategory;

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
        return mView;
    }

    private void initUI() {
        btnListViewAndGirdView = mView.findViewById(R.id.btnListViewAndGirdView);
        recyclerViewCategory = mView.findViewById(R.id.recyclerViewCategory);
        recyclerViewCategory = mView.findViewById(R.id.recyclerViewCategory);
    }
}