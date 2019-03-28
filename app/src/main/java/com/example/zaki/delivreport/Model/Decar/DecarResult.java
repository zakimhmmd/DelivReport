package com.example.zaki.delivreport.Model.Decar;

public class DecarResult {
    private boolean success;
    private String message;

    public DecarResult(boolean success, String message) {
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
