package com.robotoworks.composter.mediators;

import com.robotoworks.composter.binders.ItemBinder;
import com.robotoworks.composter.binders.StaticBinder;
import com.robotoworks.composter.binders.StaticBinding;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class BinderManager implements BinderRegistry, ViewHolderRegistry {

    private final ArrayList<StaticBinding> headerItemBinders = new ArrayList<>();
    private final HashMap<Integer, ItemBinder> itemBinders = new HashMap<>();
    private final ArrayList<StaticBinding> footerItemBinders = new ArrayList<>();
    private final HashMap<Integer, ViewHolderFactory> viewHolderFactories = new HashMap<>();

    @Override
    public void registerHeaderBinder(int viewType, StaticBinder binder) {

        headerItemBinders.add(new StaticBinding(viewType, binder));
    }

    @Override
    public void registerItemBinder(int viewType, ItemBinder binder) {

        itemBinders.put(viewType, binder);
    }

    @Override
    public void registerFooterBinder(int viewType, StaticBinder binder) {

        footerItemBinders.add(new StaticBinding(viewType, binder));
    }

    public void clearAllBinders() {

        clearHeaderBinders();
        clearItemBinders();
        clearFooterBinders();
    }

    public void clearHeaderBinders() {

        headerItemBinders.clear();
    }

    public void clearItemBinders() {

        itemBinders.clear();
    }

    public void clearFooterBinders() {

        footerItemBinders.clear();
    }

    @Nullable
    public StaticBinding getHeaderBinding(int position) {

        if (position < 0 || position >= headerItemBinders.size()) {
            return null;
        }
        return headerItemBinders.get(position);
    }

    @Nullable
    public ItemBinder getItemBinder(int viewType) {

        return itemBinders.get(viewType);
    }

    @Nullable
    public StaticBinding getFooterBinding(int position) {

        if (position < 0 || position >= footerItemBinders.size()) {
            return null;
        }
        return footerItemBinders.get(position);
    }

    public int getHeaderCount() {

        return headerItemBinders.size();
    }

    public int getFooterCount() {

        return footerItemBinders.size();
    }

    @Override
    public void registerViewHolderFactory(int viewType, ViewHolderFactory factory) {

        viewHolderFactories.put(viewType, factory);
    }

    public ViewHolderFactory getViewHolderFactory(int viewType) {

        return viewHolderFactories.get(viewType);
    }

}
