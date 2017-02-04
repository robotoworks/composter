package com.robotoworks.composter_example.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.squareup.picasso.Picasso;
import com.robotoworks.composter_example.R;

public class ImageItemViewHolder extends InjectableViewHolder {

    @BindView(R.id.image) ImageView imageView;

    public ImageItemViewHolder(View itemView) {

        super(itemView);
    }

    public void setImageUrl(String url) {

        Picasso.with(itemView.getContext()).load(url).into(imageView);
    }
}
