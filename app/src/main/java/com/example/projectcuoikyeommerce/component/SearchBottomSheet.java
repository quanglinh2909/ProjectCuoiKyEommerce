package com.example.projectcuoikyeommerce.component;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.SearchAdapter;
import com.example.projectcuoikyeommerce.dto.ProductDto;
import com.example.projectcuoikyeommerce.presenter.HomePresenter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jakewharton.rxbinding4.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class SearchBottomSheet extends BottomSheetDialogFragment {
    private BottomSheetDialog bottomSheetDialog;
    private HomePresenter homePresenter = new HomePresenter();
    private View view;
    private EditText edtSearch;
    private RecyclerView recyclerView;
    private List<ProductDto> list = new ArrayList<>();
    private SearchAdapter searchAdapter;
    private ProgressBar progressBar;
    private ImageButton btnCloseSearch;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.search_bottomsheet, null);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(false);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        initUi();
        initData();
        search();
        return bottomSheetDialog;

    }

    private void initData() {
        btnCloseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void search() {
        RxTextView.textChanges(edtSearch)
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> {
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                    if (!query.toString().trim().isEmpty()) {
                        list = homePresenter.search(query.toString());
                        searchAdapter = new SearchAdapter(list);
                        recyclerView.setAdapter(searchAdapter);
                    }else{
                        searchAdapter = new SearchAdapter(new ArrayList<>());
                        recyclerView.setAdapter(searchAdapter);
                    }
                    progressBar.setVisibility(View.GONE);
                        }
                    }, 1000);

                });

    }


    private void initUi() {
        edtSearch = view.findViewById(R.id.edtSearch);
        recyclerView = view.findViewById(R.id.recyclerviewSearch);
        progressBar = view.findViewById(R.id.progressBar);
        btnCloseSearch = view.findViewById(R.id.btnCloseSearch);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}
