package org.robotoworks.composter.mediators;

import org.robotoworks.composter.binders.ItemBinder;
import org.robotoworks.composter.binders.StaticBinder;

public interface BinderRegistry {

    void registerHeaderBinder(int viewType, StaticBinder binder);

    void registerItemBinder(int viewType, ItemBinder binder);

    void registerFooterBinder(int viewType, StaticBinder binder);
}
