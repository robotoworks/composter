package com.robotoworks.composter_example.viewholder;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.robotoworks.composter_example.R;

public class StringItemViewHolder extends InjectableViewHolder {
    @BindView(R.id.text) TextView textView;

    public StringItemViewHolder(View itemView) {

        super(itemView);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
