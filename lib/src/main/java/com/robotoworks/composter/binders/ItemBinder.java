package com.robotoworks.composter.binders;


import androidx.recyclerview.widget.RecyclerView;

public interface ItemBinder<ITEM, VIEW extends RecyclerView.ViewHolder> {

    void bindItem(ITEM item, VIEW viewHolder);
}
