package com.example.projectcuoikyeommerce.component;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.model.TagChild;
import com.example.projectcuoikyeommerce.model.TagParent;

import java.util.List;
import java.util.Map;

public class ExpandableMenuAdapter extends BaseExpandableListAdapter  {
    private List<TagParent> mListGroup;
    private Map<TagParent,List<TagChild>> mListItem;

    public ExpandableMenuAdapter(List<TagParent> mListGroup, Map<TagParent, List<TagChild>> mListItem) {
        this.mListGroup = mListGroup;
        this.mListItem = mListItem;
    }

    @Override
    public int getGroupCount() {
        if(mListGroup != null) return mListGroup.size();
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(mListGroup != null && mListItem != null) return  mListItem.get(mListGroup.get(groupPosition)).size();
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListItem.get(mListGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        TagParent tagParent = mListGroup.get(groupPosition);
        return tagParent.getIdNumber();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        TagChild tagChild = mListItem.get(mListGroup.get(groupPosition)).get(childPosition);
        return tagChild.getIdNumber();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       if(convertView == null){
           convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_group,parent,false);
       }

       TextView textView = convertView.findViewById(R.id.txtItemMenuGroup);
       TagParent tagParent = mListGroup.get(groupPosition);
       textView.setText(tagParent.getName());

        ImageView imageView = convertView.findViewById(R.id.imgDropAndUp);
        if(isExpanded){
            imageView.setImageResource((R.drawable.ic_baseline_keyboard_arrow_up_24));
        }else{
            imageView.setImageResource((R.drawable.ic_baseline_keyboard_arrow_down_24));

        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_chilren,parent,false);
        }
        TextView textView = convertView.findViewById(R.id.txtItemMenuChirent);
        TagChild tagChild = mListItem.get(mListGroup.get(groupPosition)).get(childPosition);
        textView.setText(tagChild.getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
