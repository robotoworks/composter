package com.robotoworks.composter.mediators;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public interface ViewHolderFactory {

    RecyclerView.ViewHolder createViewHolder(ViewGroup parent);
}
