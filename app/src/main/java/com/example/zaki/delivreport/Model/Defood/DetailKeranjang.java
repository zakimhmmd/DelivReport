package com.example.zaki.delivreport.Model.Defood;

import com.google.gson.annotations.SerializedName;

public class DetailKeranjang {
    String catatan;
    double harga;
    String idMenu;
    String imageUrl;
    int jumlah;
    String name;
    @SerializedName("_id")
    String id;

    public String getCatatan() {
        return catatan;
    }

    public double getHarga() {
        return harga;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
