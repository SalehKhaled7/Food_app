package com.example.foodapp;

import android.net.Uri;

public class ImageItem {
    String name;
    Uri imageUri;

    public ImageItem() {
    }

    public ImageItem( Uri imageUri) {

        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
