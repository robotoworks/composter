package com.robotoworks.composter_example.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.robotoworks.composter.binders.ItemBinder;
import com.robotoworks.composter.binders.StaticBinder;
import com.robotoworks.composter.mediators.BinderRegistrar;
import com.robotoworks.composter.mediators.BinderRegistry;
import com.robotoworks.composter.mediators.ViewHolderFactory;
import com.robotoworks.composter.mediators.ViewHolderRegistry;
import com.robotoworks.composter_example.model.ImageListItem;
import com.robotoworks.composter_example.model.StringListItem;
import com.robotoworks.composter_example.model.ViewType;
import com.robotoworks.composter_example.viewholder.ImageItemViewHolder;
import com.robotoworks.composter_example.viewholder.StringItemViewHolder;
import com.robotoworks.composter_example.R;

public class ExampleBinderRegistrar implements BinderRegistrar {

    @Override
    public void registerBinders(BinderRegistry binderRegistry) {

        binderRegistry.registerHeaderBinder(ViewType.HEADER_IMAGE.ordinal(), new StaticBinder<ImageItemViewHolder>() {
            @Override
            public void bind(ImageItemViewHolder viewHolder) {

                viewHolder.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Android_logo_(2014).svg/2000px-Android_logo_(2014).svg.png");
            }
        });

        binderRegistry.registerHeaderBinder(ViewType.HEADER_TEXT.ordinal(), new StaticBinder<StringItemViewHolder>() {
            @Override
            public void bind(StringItemViewHolder viewHolder) {

                viewHolder.setText("This is a static header!");
            }
        });

        binderRegistry.registerItemBinder(ViewType.STRING_LIST_ITEM.ordinal(), new ItemBinder<StringListItem, StringItemViewHolder>() {
            @Override
            public void bindItem(StringListItem item, StringItemViewHolder viewHolder) {

                viewHolder.setText(item.getText());
            }
        });

        binderRegistry.registerItemBinder(ViewType.IMAGE_LIST_ITEM.ordinal(), new ItemBinder<ImageListItem, ImageItemViewHolder>() {
            @Override
            public void bindItem(ImageListItem imageListItem, ImageItemViewHolder viewHolder) {

                viewHolder.setImageUrl(imageListItem.getImageUrl());
            }
        });

        binderRegistry.registerFooterBinder(ViewType.FOOTER_TEXT.ordinal(), new StaticBinder<StringItemViewHolder>() {
            @Override
            public void bind(StringItemViewHolder viewHolder) {

                viewHolder.setText("This is a static footer!");
            }
        });

        binderRegistry.registerFooterBinder(ViewType.FOOTER_IMAGE.ordinal(), new StaticBinder<ImageItemViewHolder>() {
            @Override
            public void bind(ImageItemViewHolder viewHolder) {

                viewHolder.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/f/f2/Android_M.jpg");
            }
        });
    }

    @Override
    public void registerViewHolderFactories(ViewHolderRegistry viewHolderRegistry) {

        viewHolderRegistry.registerViewHolderFactory(ViewType.HEADER_TEXT.ordinal(), new ViewHolderFactory() {
            @Override
            public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {

                return createStringViewHolder(parent);
            }
        });

        viewHolderRegistry.registerViewHolderFactory(ViewType.HEADER_IMAGE.ordinal(), new ViewHolderFactory() {
            @Override
            public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {

                return createImageViewHolder(parent);
            }
        });

        viewHolderRegistry.registerViewHolderFactory(ViewType.STRING_LIST_ITEM.ordinal(), new ViewHolderFactory() {
            @Override
            public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {

                return createStringViewHolder(parent);
            }
        });

        viewHolderRegistry.registerViewHolderFactory(ViewType.IMAGE_LIST_ITEM.ordinal(), new ViewHolderFactory() {
            @Override
            public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {

                return createImageViewHolder(parent);
            }
        });

        viewHolderRegistry.registerViewHolderFactory(ViewType.FOOTER_TEXT.ordinal(), new ViewHolderFactory() {
            @Override
            public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {

                return createStringViewHolder(parent);
            }
        });

        viewHolderRegistry.registerViewHolderFactory(ViewType.FOOTER_IMAGE.ordinal(), new ViewHolderFactory() {
            @Override
            public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {

                return createImageViewHolder(parent);
            }
        });
    }

    @NonNull
    private RecyclerView.ViewHolder createImageViewHolder(ViewGroup parent) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image, parent, false);
        ImageItemViewHolder viewHolder = new ImageItemViewHolder(view);
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder createStringViewHolder(ViewGroup parent) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_string, parent, false);
        StringItemViewHolder viewHolder = new StringItemViewHolder(view);
        return viewHolder;
    }
}
