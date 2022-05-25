package com.example.projectcuoikyeommerce.adapter.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.event.HomeEvent;
import com.example.projectcuoikyeommerce.model.TagParent;

import java.util.List;

public class AdapterTypeNavigation extends RecyclerView.Adapter<AdapterTypeNavigation.ViewHolderNavigation> {
    private List<TagParent> items;
    private HomeEvent homeEvent;
    private String textSelected = "";
    private TextView textView;

    public AdapterTypeNavigation(List<TagParent> items, HomeEvent homeEvent) {
        this.items = items;
        this.homeEvent = homeEvent;
        if (items != null && items.size() > 0) {
            textSelected = items.get(0).getName();
        }
    }


    @NonNull
    @Override
    public ViewHolderNavigation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_types_navigation, parent, false);
        return new ViewHolderNavigation(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNavigation holder, @SuppressLint("RecyclerView") int position) {
        TagParent item = items.get(position);
        if (item == null) return;
        holder.nameNavigation.setText(item.getName());
        if (item.getName().equals(textSelected)) {
            holder.mark.setVisibility(View.VISIBLE);
//            holder.nameNavigation.setTypeface(holder.nameNavigation.getTypeface(), Typeface.BOLD);
            holder.nameNavigation.setTextColor(Color.BLACK);
        } else {
            holder.mark.setVisibility(View.GONE);
        }

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeEvent.clickItemNavigation(position);
                textSelected = item.getName();
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
   public class ViewHolderNavigation extends RecyclerView.ViewHolder {
        TextView nameNavigation;
        ImageView mark;
        LinearLayout item;

        public ViewHolderNavigation(@NonNull View itemView) {
            super(itemView);
            nameNavigation = itemView.findViewById(R.id.label_navigation);
            mark = itemView.findViewById(R.id.mark_navigation);
            item = itemView.findViewById(R.id.item);
//            holder.mark.setVisibility(View.GONE);
//            holder.nameNavigation.setTypeface(holder.nameNavigation.getTypeface(), Typeface.BOLD);
        }

    }
}

