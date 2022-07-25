package com.example.projectcuoikyeommerce.adapter.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.TagParent;

import java.util.List;

public class SpinnerBSTAdapter extends BaseAdapter {
    List<Branch>  branchList;

    public SpinnerBSTAdapter(List<Branch> branchList) {
        this.branchList = branchList;
    }

    @Override
    public int getCount() {
        if(branchList == null) return 0;
        return branchList.size();
    }

    @Override
    public Object getItem(int position) {
        return branchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return branchList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner_type,parent,false);
        TextView txtItem = view.findViewById(R.id.txtItem);
        txtItem.setText(branchList.get(position).getName());
        return view;
    }
}
