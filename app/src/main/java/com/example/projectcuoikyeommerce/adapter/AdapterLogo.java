package com.example.projectcuoikyeommerce.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikyeommerce.R;

import java.util.List;

public class AdapterLogo extends RecyclerView.Adapter<ViewHolderLogo> {
    List<String> logos;
    Context context;
    public AdapterLogo(List<String> logos,Context context){
        this.logos=logos;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolderLogo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.logo,parent,false);
        ViewHolderLogo viewHolder=new ViewHolderLogo(view).linkAdapter(this);
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
    public ViewHolderLogo linkAdapter(AdapterLogo adapter){
        this.adapter=adapter;
        return this;
    }
}