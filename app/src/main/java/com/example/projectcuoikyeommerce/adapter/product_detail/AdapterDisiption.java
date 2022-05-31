package com.example.projectcuoikyeommerce.adapter.product_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.model.Description;

import java.util.List;

public class AdapterDisiption extends RecyclerView.Adapter<AdapterDisiption.ViewHolder> {
    private List<Description> descriptionList;

    public AdapterDisiption(List<Description> descriptionList) {
        this.descriptionList = descriptionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dicription,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Description description = descriptionList.get(position);
       if (description == null) return;
       if(description.getTitle() != null){
           holder.txtTitle.setText(description.getTitle());
       }
       if(description.getDescription() != null){
           holder.txtDescription.setText(description.getDescription());
       }
    }

    @Override
    public int getItemCount() {
        return descriptionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}