package com.example.projectcuoikyeommerce.adapter.admin;

import android.animation.LayoutTransition;
import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.model.Term;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {
    List<Term> terms;
    Context context;
    public TermAdapter(List<Term> terms){
        this.terms=terms;
    }
    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.term_item,parent,false);
        TermAdapter.TermViewHolder viewHolder=new TermAdapter.TermViewHolder(view);
        context = parent.getContext();
        Log.d("Voo ne","ok");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        Term term=terms.get(position);
        if(term==null) return;
        Log.d("title",term.getTitle());
        holder.title.setText(term.getTitle());
        holder.detailsText.setText(term.getContent());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                int visibility=(holder.detailsText.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
                TransitionManager.beginDelayedTransition(holder.termLayout,new AutoTransition());
                holder.detailsText.setVisibility(visibility);
            }
        });
    }

    @Override
    public int getItemCount() {
       if(terms==null) return 0;
       return terms.size();
    }

    public class TermViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView detailsText;
        LinearLayout termLayout;
        private ItemClickListener itemClickListener;
        public TermViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.termTitle);
            detailsText=itemView.findViewById(R.id.termContent);
            termLayout=itemView.findViewById(R.id.term);
            termLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition());
        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }
    }
    public interface ItemClickListener {
        void onClick(View view, int position);
    }
}
