package com.example.zaki.delivreport.Model.Defood;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class DetailNota {
    @SerializedName("image")
    @Nullable
    private String image;
    private String thumbnail;

    @Nullable
    public String getImage() {
        return image;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}

