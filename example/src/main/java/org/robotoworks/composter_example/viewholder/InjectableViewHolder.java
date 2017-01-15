package org.robotoworks.composter_example.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

public abstract class InjectableViewHolder extends RecyclerView.ViewHolder {

    public InjectableViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
