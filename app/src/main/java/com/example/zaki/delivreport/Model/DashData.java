package com.example.zaki.delivreport.Model;

import java.util.ArrayList;

public class DashData {

    private ArrayList<DashUsers> users = new ArrayList<>();
    private ArrayList<DashDrivers> drivers = new ArrayList<>();
    private ArrayList<DashTrans> trans = new ArrayList<>();

    public ArrayList<DashUsers> getUsers() {
        return users;
    }

    public ArrayList<DashDrivers> getDrivers() {
        return drivers;
    }

    public ArrayList<DashTrans> getTrans() {
        return trans;
    }
}
