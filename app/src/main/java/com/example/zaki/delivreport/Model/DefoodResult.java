package com.example.zaki.delivreport.Model;

import com.google.gson.annotations.SerializedName;

public class DefoodResult {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public DefoodResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
