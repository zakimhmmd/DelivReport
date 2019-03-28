package com.example.zaki.delivreport.Model.Depayment;

import com.google.gson.annotations.SerializedName;

public class DepayList {
    private String id;
    private String jenisMember;
    private String idMember;
    private String service;
    private String data;
    private double buy;
    private double sell;
    private String status;
    private String description;
    @SerializedName("createdAt")
    private String date;
    private String namaMember;

    public String getId() {
        return id;
    }

    public String getJenisMember() {
        return jenisMember;
    }

    public String getIdMember() {
        return idMember;
    }

    public String getService() {
        return service;
    }

    public String getData() {
        return data;
    }

    public double getBuy() {
        return buy;
    }

    public double getSell() {
        return sell;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getNamaMember() {
        return namaMember;
    }
}
