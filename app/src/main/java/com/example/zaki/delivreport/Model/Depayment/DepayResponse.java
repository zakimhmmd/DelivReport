package com.example.zaki.delivreport.Model.Depayment;

public class DepayResponse {
    private String success;
    private String message;
    private String error_code;
    private DepayData data;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getError_code() {
        return error_code;
    }

    public DepayData getData() {
        return data;
    }
}
