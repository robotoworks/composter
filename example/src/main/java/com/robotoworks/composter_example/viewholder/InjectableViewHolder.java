package com.robotoworks.composter_example.viewholder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;

public abstract class InjectableViewHolder extends RecyclerView.ViewHolder {

    public InjectableViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
