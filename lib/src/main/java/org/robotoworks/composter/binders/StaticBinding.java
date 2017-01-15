package org.robotoworks.composter.binders;

public class StaticBinding {

    private final int viewType;
    private final StaticBinder binder;

    public StaticBinding(int viewType, StaticBinder binder) {

        this.viewType = viewType;
        this.binder = binder;
    }

    public int getViewType() {

        return viewType;
    }

    public StaticBinder getBinder() {

        return binder;
    }
}
