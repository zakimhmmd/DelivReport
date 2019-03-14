package com.example.zaki.delivreport.Model;

import java.util.ArrayList;

public class DecarData {
    private DecarStats stats;
    private ArrayList<DecarListData> list = new ArrayList<>();

    public DecarStats getStats() {
        return stats;
    }

    public ArrayList<DecarListData> getList() {
        return list;
    }
}
