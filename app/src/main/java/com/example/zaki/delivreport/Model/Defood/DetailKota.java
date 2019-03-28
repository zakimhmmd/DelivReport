package com.example.zaki.delivreport.Model.Defood;

import com.google.gson.annotations.SerializedName;

public class DetailKota {
    String id;
    String updatedAt;
    String createdAt;
    String cityName;
    String cityCode;
    @SerializedName("__v")
    String v;
    String distance;

    public String getId() {
        return id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getV() {
        return v;
    }

    public String getDistance() {
        return distance;
    }
}
