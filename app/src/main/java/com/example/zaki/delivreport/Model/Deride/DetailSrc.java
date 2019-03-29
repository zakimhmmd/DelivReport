package com.example.zaki.delivreport.Model.Deride;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class DetailSrc {
    @SerializedName("_id")
    private String id;
    private String thumbnail;
    @Nullable
    private String image;

    public String getId() {
        return id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    @Nullable
    public String getImage() {
        return image;
    }
}
