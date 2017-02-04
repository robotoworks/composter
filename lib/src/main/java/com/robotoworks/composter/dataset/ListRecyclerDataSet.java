package com.robotoworks.composter.dataset;

import java.util.List;

public abstract class ListRecyclerDataSet<ITEM> implements RecyclerDataset {

    private List<ITEM> items;

    public ListRecyclerDataSet(List<ITEM> items) {

        this.items = items;
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    @Override
    public ITEM getItem(int position) {

        return items.get(position);
    }
}
