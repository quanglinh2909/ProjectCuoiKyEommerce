package com.example.projectcuoikyeommerce.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.event.EventGetAddress;
import com.example.projectcuoikyeommerce.model.UserInfo;

import java.util.List;

public class GetAddressAdapter extends RecyclerView.Adapter<GetAddressAdapter.ViewHolder> {
    private List<UserInfo> userInfoList;
    private EventGetAddress eventGetAddress;
    private UserInfo mUserInfo;

    public GetAddressAdapter(List<UserInfo> userInfoList, EventGetAddress eventGetAddress,UserInfo userInfo) {
        this.userInfoList = userInfoList;
        this.eventGetAddress = eventGetAddress;
        this.mUserInfo = userInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_address,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        UserInfo userInfo = userInfoList.get(position);
        if(userInfo == null) return;
        holder.txtLocation.setText(userInfo.getFullName()+" | (+84) "+ userInfo.getTelephone()
                +", "+userInfo.getAddress()+", "+ userInfo.getWard()+", "+ userInfo.getDistrict()+", "
                +userInfo.getProvince());
        if(userInfo.getDefault() == 0) holder.txtDefault.setVisibility(View.GONE);
        if(userInfo.equals(mUserInfo)) holder.checkSelect.setChecked(true);

        holder.txtLocation.setOnClickListener(v -> eventGetAddress.clickSelectAddress(position,userInfo));
        holder.checkSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked == true) eventGetAddress.clickSelectAddress(position,userInfo);
        });
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton checkSelect;
        TextView txtLocation,txtUpdate,txtDefault;
        View lineBottom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkSelect= itemView.findViewById(R.id.checkSelect);
            txtUpdate= itemView.findViewById(R.id.txtUpdate);
            txtDefault= itemView.findViewById(R.id.txtDefault);
            lineBottom= itemView.findViewById(R.id.lineBottom);
            txtLocation= itemView.findViewById(R.id.txtLocation);
        }
    }
}
