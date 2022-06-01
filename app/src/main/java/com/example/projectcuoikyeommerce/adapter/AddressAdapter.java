package com.example.projectcuoikyeommerce.adapter;

import android.location.Address;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectcuoikyeommerce.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private List<Address> list;

    public AddressAdapter(List<Address> list) {
        this.list = list;
    }
    public void setData(List<Address> addressList){
        this.list = addressList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Address address = list.get(position);
        Log.d("AAA", "onBindViewHolder: "+ address.toString());
        if(address != null){
            String addressLine= address.getAddressLine(0);

            holder.txtNameAddress.setText(addressLine);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameAddress = itemView.findViewById(R.id.txtAddress);
        }
    }
}
