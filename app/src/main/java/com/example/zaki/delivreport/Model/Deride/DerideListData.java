package com.example.zaki.delivreport.Model.Deride;

import com.google.gson.annotations.SerializedName;

public class DerideListData {
    int id;
    double price;
    String status;
    String namaDriver;
    String namaUser;
    @SerializedName("createdAt")
    String tanggal;

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getNamaDriver() {
        return namaDriver;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public String getTanggal() {
        return tanggal;
    }
}
