package com.example.zaki.delivreport.Model.Deexpress;

public class DetailResponse {
    private String success;
    private String message;
    private String error_code;
    private DetailData data;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getError_code() {
        return error_code;
    }

    public DetailData getData() {
        return data;
    }
}
