package com.example.projectcuoikyeommerce.component;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;
import java.util.Map;

public class ExpandableMenuAdapter extends BaseExpandableListAdapter  {
    private List mListGroup;
    private Map mListItem;

    @Override
    public int getGroupCount() {
        if(mListGroup != null) return mListGroup.size();
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        if(mListGroup != null && mListItem != null) return  mListItem.get(mListGroup.get(groupPosition)).size();
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
//        return mListItem.get(mListGroup.get(groupPosition)).get(childPosition);
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
