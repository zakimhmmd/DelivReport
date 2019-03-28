package com.example.zaki.delivreport.Model.Deride;

import com.google.gson.annotations.SerializedName;

public class DetailData {
    private String id;
    private String idUser;
    private String idDriver;
    private String picklat;
    private String picklon;
    private String pickAddress;
    private String destlat;
    private String destlon;
    private String destAddress;
    private String vehicle;
    private String distance;
    private String price;
    private String discount;
    private String voucher;
    private String payment;
    private String note;
    private String status;
    private String reasonCancel;
    @SerializedName("createdAt")
    private String startTime;
    @SerializedName("updatedAt")
    private String endTime;
    private DetailDriver driver;
    private DetailUser user;
    private DetailKota kota;

    public String getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public String getPicklat() {
        return picklat;
    }

    public String getPicklon() {
        return picklon;
    }

    public String getPickAddress() {
        return pickAddress;
    }

    public String getDestlat() {
        return destlat;
    }

    public String getDestlon() {
        return destlon;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getDistance() {
        return distance +" Km";
    }

    public String getPrice() {
        return "Rp"+price;
    }

    public String getDiscount() {
        return "Rp"+discount;
    }

    public String getVoucher() {
        return voucher;
    }

    public String getPayment() {
        return payment;
    }

    public String getNote() {
        return note;
    }

    public String getStatus() {
        return status;
    }

    public String getReasonCancel() {
        return reasonCancel;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public DetailDriver getDriver() {
        return driver;
    }

    public DetailUser getUser() {
        return user;
    }

    public DetailKota getKota() {
        return kota;
    }
}
