package com.example.projectcuoikyeommerce.adapter.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.model.TagParent;

import java.util.List;

public class SpinnerTypeAdapter extends BaseAdapter {
    List<TagParent> parentList;

    public SpinnerTypeAdapter(List<TagParent> parentList) {
        this.parentList = parentList;
    }

    @Override
    public int getCount() {
        if(parentList == null) return 0;
        return parentList.size();
    }

    @Override
    public Object getItem(int position) {
        return parentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return parentList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner_type,parent,false);
        TextView txtItem = view.findViewById(R.id.txtItem);
        txtItem.setText(parentList.get(position).getName());
        return view;
    }
}
