package com.example.zaki.delivreport.Model.Defood;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailCart {
    @SerializedName("_id")
    String id;
    String updatedAt;
    String createdAt;
    int idTransaksi;
    String idRestoran;
    @SerializedName("__v")
    String  v;
    String status;
    ArrayList<DetailKeranjang> keranjang;

    public String getId() {
        return id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public String getIdRestoran() {
        return idRestoran;
    }

    public String getV() {
        return v;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<DetailKeranjang> getKeranjang() {
        return keranjang;
    }
}
