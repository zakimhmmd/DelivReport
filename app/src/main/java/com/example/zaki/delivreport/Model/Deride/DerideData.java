package com.example.zaki.delivreport.Model.Deride;

import java.util.ArrayList;

public class DerideData {
    private DerideStats stats;
    private ArrayList<DerideListData> list = new ArrayList<>();

    public DerideStats getStats() {
        return stats;
    }

    public ArrayList<DerideListData> getList() {
        return list;
    }
}
