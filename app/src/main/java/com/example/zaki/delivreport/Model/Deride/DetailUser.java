package com.example.zaki.delivreport.Model.Deride;

import com.google.gson.annotations.SerializedName;

public class DetailUser {
    @SerializedName("_id")
    private String id;
    private String name;
    private String phone;
    private String email;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
