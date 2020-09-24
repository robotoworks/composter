package com.robotoworks.composter_example.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.robotoworks.composter_example.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class ImageItemViewHolder extends InjectableViewHolder {

    @BindView(R.id.image)
    ImageView imageView;

    public ImageItemViewHolder(View itemView) {

        super(itemView);
    }

    public void setImageUrl(String url) {

        Picasso.get().load(url).into(imageView);
    }
}
