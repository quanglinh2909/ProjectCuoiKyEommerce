package com.example.projectcuoikyeommerce.component;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.event.MenuEvent;
import com.example.projectcuoikyeommerce.model.TagChild;
import com.example.projectcuoikyeommerce.model.TagParent;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuBottomSheet extends BottomSheetDialogFragment {
    private ExpandableListView expandableListViewMenu;
    private List<TagParent> parentList;
    private Map<TagParent, List<TagChild>> mListItem;
    private ExpandableMenuAdapter expandableMenuAdapter;
    private View view;
    private ImageButton btnCloseMenu;
    private BottomSheetDialog bottomSheetDialog;
    private MenuEvent menuEvent;

    public MenuBottomSheet(MenuEvent menuEvent) {
        this.menuEvent = menuEvent;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.menu_bottom_sheet, null);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(false);


        initUi();
        handleAction();
        getmListItem();
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        return bottomSheetDialog;
    }


    private void initUi() {
        expandableListViewMenu = view.findViewById(R.id.expandableListViewMenu);
        btnCloseMenu = view.findViewById(R.id.btnCloseMenu);
    }

    private void handleAction() {
        btnCloseMenu.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });
    }


    private Map<TagParent, List<TagChild>> getmListItem() {
        Map<TagParent, List<TagChild>> tagParentListMap = new HashMap<>();


        ApiUtils.tagChild().getListTagChild().enqueue(new Callback<List<TagChild>>() {
            @Override
            public void onResponse(Call<List<TagChild>> call, Response<List<TagChild>> response) {

                List<TagChild> tagChildList = response.body();
                for (TagChild t :tagChildList) {
                    TagParent tagParent = t.getIdTagParent();
                    if(tagParentListMap.containsKey(tagParent)){
                        List<TagChild> list = tagParentListMap.get(tagParent);
                        list.add(t);
                        tagParentListMap.put(t.getIdTagParent(), list);
                    }else{
                        List<TagChild> list = new ArrayList<>();
                        list.add(t);
                        tagParentListMap.put(t.getIdTagParent(), list);
                    }
                }
                mListItem = tagParentListMap;
                ApiUtils.tagParent().listTagParent().enqueue(new Callback<List<TagParent>>() {
                    @Override
                    public void onResponse(Call<List<TagParent>> call, Response<List<TagParent>> response) {
                        parentList = response.body();
                        expandableMenuAdapter = new ExpandableMenuAdapter(parentList, mListItem,menuEvent);
                        expandableListViewMenu.setAdapter(expandableMenuAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<TagParent>> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onFailure(Call<List<TagChild>> call, Throwable t) {

            }
        });

        return tagParentListMap;

    }
}
