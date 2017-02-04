package com.robotoworks.composter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.robotoworks.composter.binders.ItemBinder;
import com.robotoworks.composter.binders.StaticBinding;
import com.robotoworks.composter.dataset.RecyclerDataset;
import com.robotoworks.composter.mediators.BinderManager;
import com.robotoworks.composter.mediators.BinderRegistrar;
import com.robotoworks.composter.mediators.ViewHolderFactory;

public class RecyclerViewAdapter<ITEM> extends RecyclerView.Adapter {

    private RecyclerDataset<ITEM> data;
    private final BinderManager binderManager;
    private final BinderRegistrar binderRegistrar;

    public RecyclerViewAdapter(BinderManager binderManager, BinderRegistrar binderRegistrar) {

        this.binderManager = binderManager;
        this.binderRegistrar = binderRegistrar;
        binderRegistrar.registerViewHolderFactories(binderManager);
    }

    public void setSource(RecyclerDataset<ITEM> data) {

        binderManager.clearAllBinders();
        this.data = data;
        binderRegistrar.registerBinders(binderManager);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final ViewHolderFactory factory = binderManager.getViewHolderFactory(viewType);
        if (factory == null) {
            throw new IllegalStateException(String.format("Missing view holder factory for viewType %d!", viewType));
        }
        return factory.createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final int headerCount = binderManager.getHeaderCount();
        ITEM item = null;
        if (position < headerCount) {
            final StaticBinding headerBinding = binderManager.getHeaderBinding(position);
            if (headerBinding == null) {
                throw new IllegalStateException(String.format("Missing binder for header at position %d!", position));
            }
            headerBinding.getBinder().bind(holder);
        } else {
            final int itemCount = data.getItemCount();
            final int headersPlusItems = headerCount + itemCount;
            if (position < headersPlusItems) {
                if (data == null) {
                    return;
                }
                final int itemViewType = data.getItemViewType(position - headerCount);
                ItemBinder itemBinder = binderManager.getItemBinder(itemViewType);
                item = data.getItem(position - headerCount);
                if (itemBinder == null) {
                    throw new IllegalStateException(String.format("Missing binder for item of viewType %d at position %d!", itemViewType, position));
                }
                itemBinder.bindItem(item, holder);
            } else {
                final StaticBinding footerBinding = binderManager.getFooterBinding(position - headersPlusItems);
                if (footerBinding == null) {
                    throw new IllegalStateException(String.format("Missing binder for footer at position %d!", position));
                }
                footerBinding.getBinder().bind(holder);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {

        final int headerCount = binderManager.getHeaderCount();
        if (position < headerCount) {
            return binderManager.getHeaderBinding(position).getViewType();
        } else {
            final int itemCount = data.getItemCount();
            final int headersPlusItems = headerCount + itemCount;
            if (position < headersPlusItems) {
                return data.getItemViewType(position - headerCount);
            } else {
                return binderManager.getFooterBinding(position - headersPlusItems).getViewType();
            }
        }
    }

    @Override
    public int getItemCount() {

        return binderManager.getHeaderCount() + data.getItemCount() + binderManager.getFooterCount();
    }
}
