package com.robotoworks.composter.mediators;

import com.robotoworks.composter.binders.ItemBinder;
import com.robotoworks.composter.binders.StaticBinder;

public interface BinderRegistry {

    void registerHeaderBinder(int viewType, StaticBinder binder);

    void registerItemBinder(int viewType, ItemBinder binder);

    void registerFooterBinder(int viewType, StaticBinder binder);
}
