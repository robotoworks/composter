package com.robotoworks.composter.dataset;

import android.support.annotation.Nullable;

import java.util.List;

public abstract class ListRecyclerDataSet<ITEM> implements RecyclerDataset {

    private List<ITEM> items;

    public ListRecyclerDataSet(List<ITEM> items) {

        this.items = items;
    }

    @Override
    public int getItemCount() {

        return items != null ? items.size() : 0;
    }

    @Nullable
    @Override
    public ITEM getItem(int position) {

        return items != null ? items.get(position) : null;
    }
}
