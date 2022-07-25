package com.example.projectcuoikyeommerce.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.event.AddressEvent;
import com.example.projectcuoikyeommerce.model.Province;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SelectAddressAdapter extends RecyclerView.Adapter<SelectAddressAdapter.ViewHolder>{
    private List<Province> list;
    private AddressEvent addressEvent;

    public SelectAddressAdapter(List<Province> list,AddressEvent addressEvent) {
        this.list = list;
        this.addressEvent =addressEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_address,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Province province = list.get(position);
        if(province == null) return;
        holder.textView.setText(province.getName());
        holder.textView.setOnClickListener(v -> addressEvent.selectProvince(position,province));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtAddress);

        }
    }
}
