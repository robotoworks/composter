package com.robotoworks.composter.binders;


import androidx.recyclerview.widget.RecyclerView;

public interface StaticBinder<VIEW extends RecyclerView.ViewHolder> {

    void bind(VIEW viewHolder);
}
