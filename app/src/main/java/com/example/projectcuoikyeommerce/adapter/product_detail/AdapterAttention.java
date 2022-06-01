package com.example.projectcuoikyeommerce.adapter.product_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.model.Attention;

import java.util.List;

public class AdapterAttention extends RecyclerView.Adapter<AdapterAttention.ViewHolder> {
    private List<Attention> attentionList;

    public AdapterAttention(List<Attention> attentionList) {
        this.attentionList = attentionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attention,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Attention attention = attentionList.get(position);
        holder.txtNote.setText(attention.getNote());

    }

    @Override
    public int getItemCount() {
        return attentionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageIconAttention;
        TextView txtNote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIconAttention = itemView.findViewById(R.id.imageIconAttention);
            txtNote = itemView.findViewById(R.id.txtNote);
        }
    }
}
