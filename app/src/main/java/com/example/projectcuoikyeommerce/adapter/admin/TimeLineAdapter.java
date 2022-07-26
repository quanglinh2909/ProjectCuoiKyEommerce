package com.example.projectcuoikyeommerce.adapter.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.home.AdapterLogo;
import com.example.projectcuoikyeommerce.constant.URL;
import com.example.projectcuoikyeommerce.model.TimeLine;

import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {
    List<TimeLine> timeLines;
    Context context;

    public TimeLineAdapter(List<TimeLine> timeLines) {
        this.timeLines = timeLines;
    }

    @NonNull
    @Override
    public TimeLineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_line_item, parent, false);
        TimeLineAdapter.ViewHolder viewHolder = new TimeLineAdapter.ViewHolder(view);
        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimeLine timeLine = timeLines.get(position);
        if (timeLine == null) return;
        Glide.with(context).load(timeLine.getImg()).into(holder.imageView);
        holder.header.setText(timeLine.getHeader());
        holder.description.setText(timeLine.getDescription());
    }


    @Override
    public int getItemCount() {
        if (timeLines == null) return 0;
        else return timeLines.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView header;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.historyImage);
            header = itemView.findViewById(R.id.historyTextHeader);
            description = itemView.findViewById(R.id.historyTextDescip);
        }
    }
}
