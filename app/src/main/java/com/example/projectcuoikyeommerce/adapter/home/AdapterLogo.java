package com.example.projectcuoikyeommerce.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.event.home.BranchAction;

import java.util.List;

public class AdapterLogo extends RecyclerView.Adapter<ViewHolderLogo> {
    List<String> logos;
    Context context;
    BranchAction branchAction;

    public AdapterLogo(List<String> logos, Context context, BranchAction branchAction) {
        this.logos = logos;
        this.context = context;
        this.branchAction = branchAction;
    }

    @NonNull
    @Override
    public ViewHolderLogo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.logo,parent,false);
        ViewHolderLogo viewHolder=new ViewHolderLogo(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLogo holder, int position) {
        String logo=logos.get(position);
        if(logo==null) return;
        int drawableId = context.getResources().getIdentifier(logo, "drawable", context.getPackageName());
        holder.logoImage.setImageResource(drawableId);
    }

    @Override
    public int getItemCount() {
        return logos.size();
    }
}
class ViewHolderLogo extends RecyclerView.ViewHolder{
    AdapterLogo adapter;
    ImageView logoImage;
    public ViewHolderLogo(@NonNull View itemView) {
        super(itemView);
        logoImage=itemView.findViewById(R.id.logo_image);
    }

}