package com.example.projectcuoikyeommerce.component;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.recyclerview.widget.RecyclerView;

public class AnimationRecyclerView {
    public static void setAnimation(Context context, int animationResource, RecyclerView recyclerView){
        LayoutAnimationController layout = AnimationUtils.loadLayoutAnimation(context,animationResource);
        recyclerView.setLayoutAnimation(layout);
    }
}
