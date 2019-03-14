package com.example.zaki.delivreport.Model;

import java.util.ArrayList;

public class DeexpresData {
    private DeexpressStats stats;
    private ArrayList<DeexpressListData> list = new ArrayList<>();

    public DeexpressStats getStats() {
        return stats;
    }

    public ArrayList<DeexpressListData> getList() {
        return list;
    }
}
