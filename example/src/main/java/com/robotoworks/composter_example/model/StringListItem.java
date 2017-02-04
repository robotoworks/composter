package com.robotoworks.composter_example.model;

public class StringListItem implements ListItem {

    private String text;

    public StringListItem(String text) {

        this.text = text;
    }

    @Override
    public int getViewType() {

        return ViewType.STRING_LIST_ITEM.ordinal();
    }

    public String getText() {

        return text;
    }
}
