package org.robotoworks.composter.binders;

import android.support.v7.widget.RecyclerView;

public interface ItemBinder<ITEM, VIEW extends RecyclerView.ViewHolder> {

    void bindItem(ITEM item, VIEW viewHolder);
}
