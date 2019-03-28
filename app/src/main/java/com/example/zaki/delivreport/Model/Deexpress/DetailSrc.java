package com.example.zaki.delivreport.Model.Deexpress;

import com.google.gson.annotations.SerializedName;

public class DetailSrc {
    private String image;
    private String thumbnail;
    @SerializedName("_id")
    private String id;

    public String getImage() {
        return image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getId() {
        return id;
    }
}
