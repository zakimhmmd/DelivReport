package com.example.zaki.delivreport.Model.Deride;

import com.google.gson.annotations.SerializedName;

public class DetailDriver {
    @SerializedName("_id")
    private String id;
    private String name;
    private String phone;
    private DetailSrc src;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public DetailSrc getSrc() {
        return src;
    }
}
