package com.example.zaki.delivreport.Model;

import java.util.ArrayList;

public class DecarData {
    public static String[][] data = new String[][]{
            {"#4444", "4444", "Zaki", "Riza", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"},
            {"#4444", "4444", "Zaki", "Riza", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"},
            {"#4444", "4444", "Zaki", "Riza", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"}
    };

    public static ArrayList<Decar> getListData() {
        ArrayList<Decar> list = new ArrayList<>();
        for (String[] adata : data){
            Decar decar = new Decar();
            decar.setId(adata[0]);
            decar.setNo(adata[1]);
            decar.setUser(adata[2]);
            decar.setDriver(adata[3]);
            decar.setOngkir(adata[4]);
            decar.setStatus(adata[5]);
            decar.setTanggal(adata[6]);

            list.add(decar);
        }
        return list;
    }
}
