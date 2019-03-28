package com.example.zaki.delivreport.Model.Deexpress;

import com.google.gson.annotations.SerializedName;

public class DeexpressResponse {
    private boolean success;
    private String message;
    @SerializedName("error_code")
    private String errorCode;
    private DeexpresData data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public DeexpresData getData() {
        return data;
    }
}
