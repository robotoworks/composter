package com.robotoworks.composter.mediators;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface ViewHolderFactory {

    RecyclerView.ViewHolder createViewHolder(ViewGroup parent);
}
