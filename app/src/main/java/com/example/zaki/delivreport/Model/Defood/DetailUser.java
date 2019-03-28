package com.example.zaki.delivreport.Model.Defood;

import com.google.gson.annotations.SerializedName;

public class DetailUser {
    @SerializedName("_id")
    String id;
    String name;
    String email;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
