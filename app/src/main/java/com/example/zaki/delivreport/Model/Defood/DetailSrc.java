package com.example.zaki.delivreport.Model.Defood;

import com.google.gson.annotations.SerializedName;

public class DetailSrc {
    @SerializedName("_id")
    String id;
    String thumbnail;
    String image;

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
