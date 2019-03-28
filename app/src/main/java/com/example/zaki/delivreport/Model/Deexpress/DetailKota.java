package com.example.zaki.delivreport.Model.Deexpress;

import com.google.gson.annotations.SerializedName;

public class DetailKota {
    @SerializedName("_id")
    private String id;
    @SerializedName("createdAt")
    private String startTime;
    @SerializedName("updatedAt")
    private String endTime;
    private String cityName;
    private String cityCode;
    private String distance;

    public String getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getDistance() {
        return distance;
    }
}
