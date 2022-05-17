package com.example.projectcuoikyeommerce.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.component.ItemNavigation;

import java.util.List;

public class AdapterTypeNavigation extends RecyclerView.Adapter<ViewHolderNavigation>{
    List<ItemNavigation> items;
    public AdapterTypeNavigation(List<ItemNavigation> items){
        this.items=items;
    }
    @NonNull
    @Override
    public ViewHolderNavigation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.types_navigation,parent,false);
        ViewHolderNavigation viewHolder=new ViewHolderNavigation(view).linkAdapter(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNavigation holder, int position) {
        ItemNavigation item=items.get(position);
        if(item==null) return;
        holder.nameNavigation.setText(item.getName());
        if(!item.isCheck()){
            holder.mark.setVisibility(View.INVISIBLE);
        }
        else holder.nameNavigation.setTypeface( holder.nameNavigation.getTypeface(), Typeface.BOLD);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
class ViewHolderNavigation extends RecyclerView.ViewHolder{
    AdapterTypeNavigation adapter;
    TextView nameNavigation;
    ImageView mark;
    public ViewHolderNavigation(@NonNull View itemView) {
        super(itemView);
        nameNavigation=itemView.findViewById(R.id.label_navigation);
        mark=itemView.findViewById(R.id.mark_navigation);
    }
    public ViewHolderNavigation linkAdapter(AdapterTypeNavigation adapter){
        this.adapter=adapter;
        return this;
    }
}