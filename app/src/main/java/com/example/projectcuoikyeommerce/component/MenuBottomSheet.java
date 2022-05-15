package com.example.projectcuoikyeommerce.component;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.model.TagChild;
import com.example.projectcuoikyeommerce.model.TagParent;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuBottomSheet extends BottomSheetDialogFragment {
    private ExpandableListView expandableListViewMenu;
    private List<TagParent> parentList;
    private Map<TagParent, List<TagChild>> mListItem;
    private ExpandableMenuAdapter expandableMenuAdapter;
    private View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.menu_bottom_sheet, null);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(false);


        initUi();
        mListItem = getmListItem();
        parentList = new ArrayList<>(mListItem.keySet());
        expandableMenuAdapter = new ExpandableMenuAdapter(parentList, mListItem);
        expandableListViewMenu.setAdapter(expandableMenuAdapter);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        return bottomSheetDialog;
    }

    private void initUi() {
        expandableListViewMenu = view.findViewById(R.id.expandableListViewMenu);
    }

    private Map<TagParent, List<TagChild>> getmListItem() {

        List<TagChild> tagChildList1 = new ArrayList<>();
        tagChildList1.add(new TagChild("1", "1", "Item 1", 1));
        tagChildList1.add(new TagChild("1", "1", "Item 2", 2));
        tagChildList1.add(new TagChild("1", "1", "Item 3", 3));

        List<TagChild> tagChildList2 = new ArrayList<>();
        tagChildList2.add(new TagChild("1", "1", "Item 4", 4));
        tagChildList2.add(new TagChild("1", "1", "Item 5", 5));
        tagChildList2.add(new TagChild("1", "1", "Item 6", 6));

        List<TagChild> tagChildList3 = new ArrayList<>();
        tagChildList3.add(new TagChild("1", "1", "Item 7", 7));
        tagChildList3.add(new TagChild("1", "1", "Item 8", 8));
        tagChildList3.add(new TagChild("1", "1", "Item 9", 9));

        TagParent tagParent1 = new TagParent("1", "parrent 1", 1);
        TagParent tagParent2 = new TagParent("2", "parrent 2", 2);
        TagParent tagParent3 = new TagParent("3", "parrent 3", 3);

        Map<TagParent, List<TagChild>> tagParentListMap = new HashMap<>();
        tagParentListMap.put(tagParent1, tagChildList1);
        tagParentListMap.put(tagParent2, tagChildList2);
        tagParentListMap.put(tagParent3, tagChildList3);


        return tagParentListMap;

    }
}
