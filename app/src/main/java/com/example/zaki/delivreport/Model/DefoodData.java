package com.example.zaki.delivreport.Model;

import java.util.ArrayList;

public class DefoodData {
    private DefoodStats stats;
    private ArrayList<DefoodListData> list = new ArrayList<>();

    public DefoodStats getStats() {
        return stats;
    }

    public ArrayList<DefoodListData> getList() {
        return list;
    }
}
