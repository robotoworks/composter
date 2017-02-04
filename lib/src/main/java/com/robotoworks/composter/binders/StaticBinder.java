package com.robotoworks.composter.binders;

import android.support.v7.widget.RecyclerView;

public interface StaticBinder<VIEW extends RecyclerView.ViewHolder> {

    void bind(VIEW viewHolder);
}
