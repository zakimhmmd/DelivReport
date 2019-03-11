package com.example.zaki.delivreport.Model;

import com.google.gson.annotations.SerializedName;

public class DerideResponse {
    private boolean success;
    private String message;
    @SerializedName("error_code")
    private String errorCode;
    private DerideData data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public DerideData getData() {
        return data;
    }
}
