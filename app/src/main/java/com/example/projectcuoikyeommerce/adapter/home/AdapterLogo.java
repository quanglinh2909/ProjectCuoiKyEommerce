package com.example.projectcuoikyeommerce.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.constant.URL;
import com.example.projectcuoikyeommerce.fragment.HomeFragment;
import com.example.projectcuoikyeommerce.model.Branch;

import java.util.List;

public class AdapterLogo extends RecyclerView.Adapter<AdapterLogo.ViewHolderLogo> {
    List<Branch> logos;
    Context context;

    public AdapterLogo(List<Branch> logos) {
        this.logos = logos;
    }

    @NonNull
    @Override
    public ViewHolderLogo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.logo,parent,false);
        ViewHolderLogo viewHolder=new ViewHolderLogo(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLogo holder, int position) {
        Branch branch=logos.get(position);
        if(branch==null) return;
        Glide.with(context).load(URL.BASE_URL+branch.getImage()).into(holder.logoImage);

    }

    @Override
    public int getItemCount() {
        return logos.size();
    }
    class ViewHolderLogo extends RecyclerView.ViewHolder{
        ImageView logoImage;
        public ViewHolderLogo(@NonNull View itemView) {
            super(itemView);
            logoImage=itemView.findViewById(R.id.logo_image);
        }
}


}