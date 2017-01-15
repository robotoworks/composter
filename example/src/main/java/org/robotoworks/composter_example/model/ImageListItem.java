package org.robotoworks.composter_example.model;

public class ImageListItem implements ListItem {

    private String imageUrl;

    public ImageListItem(String imageUrl) {

        this.imageUrl = imageUrl;
    }

    @Override
    public int getViewType() {

        return ViewType.IMAGE_LIST_ITEM.ordinal();
    }

    public String getImageUrl() {

        return imageUrl;
    }
}
