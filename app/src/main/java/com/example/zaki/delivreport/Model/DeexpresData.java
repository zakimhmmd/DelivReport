package com.example.zaki.delivreport.Model;

import java.util.ArrayList;

public class DeexpresData {
    public static String[][] data = new String[][]{
            {"#4444", "4444", "Zaki", "Riza", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"},
            {"#4444", "4444", "Zaki", "Riza", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"},
            {"#4444", "4444", "Zaki", "Riza", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"}
    };

    public static ArrayList<Deexpres> getListData(){
        ArrayList<Deexpres> list = new ArrayList<>();
        for (String[] adata : data) {
            Deexpres deexpres = new Deexpres();
            deexpres.setId(adata[0]);
            deexpres.setNo(adata[1]);
            deexpres.setUser(adata[2]);
            deexpres.setDriver(adata[3]);
            deexpres.setOngkir(adata[4]);
            deexpres.setStatus(adata[5]);
            deexpres.setTanggal(adata[6]);

            list.add(deexpres);
        }
        return list;
    }
}
