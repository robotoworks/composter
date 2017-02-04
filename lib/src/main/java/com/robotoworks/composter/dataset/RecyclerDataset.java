package com.robotoworks.composter.dataset;

public interface RecyclerDataset<ITEM> {

    int getItemCount();

    ITEM getItem(int position);

    int getItemViewType(int position);
}
