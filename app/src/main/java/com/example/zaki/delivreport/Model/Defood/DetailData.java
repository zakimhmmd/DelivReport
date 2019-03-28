package com.example.zaki.delivreport.Model.Defood;

import com.google.gson.annotations.SerializedName;

public class DetailData {
    private String id;
    private String status;
    private String alamatPengiriman;
    private String catatan;
    private double jarak;
    @SerializedName("reasonCancel")
    private String alasanCancel;
    private DetailNota nota;
    private DetailKota kota;
    private String payment;
    private String kode;
    private double ongkir;
    private double diskon;
    private double hargaEstimasi;
    private double hargaFinal;
    private String createdAt;
    private String updatedAt;
    private DetailDriver driver;
    private String lokasiDriver;
    private DetailRestoran restoran;
    private DetailUser user;
    private DetailCart cart;

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getAlamatPengiriman() {
        return alamatPengiriman;
    }

    public String getCatatan() {
        return catatan;
    }

    public double getJarak() {
        return jarak;
    }

    public String getAlasanCancel() {
        return alasanCancel;
    }

    public DetailNota getNota() {
        return nota;
    }

    public DetailKota getKota() {
        return kota;
    }

    public String getPayment() {
        return payment;
    }

    public String getKode() {
        return kode;
    }

    public double getOngkir() {
        return ongkir;
    }

    public double getDiskon() {
        return diskon;
    }

    public double getHargaEstimasi() {
        return hargaEstimasi;
    }

    public double getHargaFinal() {
        return hargaFinal;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public DetailDriver getDriver() {
        return driver;
    }

    public String getLokasiDriver() {
        return lokasiDriver;
    }

    public DetailRestoran getRestoran() {
        return restoran;
    }

    public DetailUser getUser() {
        return user;
    }

    public DetailCart getCart() {
        return cart;
    }
}
