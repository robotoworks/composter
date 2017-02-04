package com.robotoworks.composter.mediators;

public interface BinderRegistrar {

    void registerBinders(BinderRegistry binderRegistry);

    void registerViewHolderFactories(ViewHolderRegistry viewHolderRegistry);
}
