package com.example.zaki.delivreport.Model;

public class DefoodListData {

    private int id;
    private double foods;
    private double delivery;
    private String status;
    private String namaUser;
    private String namaDriver;
    private String namaRestoran;
    private String updatedAt;

    public int getId() {
        return id;
    }

    public double getFoods() {
        return foods;
    }

    public double getDelivery() {
        return delivery;
    }

    public String getStatus() {
        return status;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public String getNamaDriver() {
        return namaDriver;
    }

    public String getNamaRestoran() {
        return namaRestoran;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
