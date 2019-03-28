package com.example.zaki.delivreport.Model.Deexpress;

public class DeexpressResult {
    private boolean success;
    private String message;

    public DeexpressResult(boolean success, String message) {
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
