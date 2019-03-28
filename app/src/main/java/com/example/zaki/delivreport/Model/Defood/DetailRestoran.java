package com.example.zaki.delivreport.Model.Defood;

import com.google.gson.annotations.SerializedName;

public class DetailRestoran {
    @SerializedName("_id")
    String id;
    String name;
    String alamat;
    String  phone;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlamat() {
        return alamat;
    }

    public String  getPhone() {
        return phone;
    }
}
