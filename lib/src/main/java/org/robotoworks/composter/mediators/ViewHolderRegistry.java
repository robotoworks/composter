package org.robotoworks.composter.mediators;

public interface ViewHolderRegistry {

    void registerViewHolderFactory(int viewType, ViewHolderFactory factory);
}
