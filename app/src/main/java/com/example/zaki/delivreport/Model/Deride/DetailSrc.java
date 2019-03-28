package com.example.zaki.delivreport.Model.Deride;

import com.google.gson.annotations.SerializedName;

public class DetailSrc {
    @SerializedName("_id")
    private String id;
    private String thumbnail;
    private String image;

    public String getId() {
        return id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getImage() {
        return image;
    }
}
