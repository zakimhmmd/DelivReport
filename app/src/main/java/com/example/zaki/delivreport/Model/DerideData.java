package com.example.zaki.delivreport.Model;

import java.util.ArrayList;

public class DerideData {
    public static String[][] data = new String[][]{
            {"#4444", "4444", "Zaki", "Riza", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"},
            {"#4444", "4444", "Zaki", "Riza", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"},
            {"#4444", "4444", "Zaki", "Riza", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"}
    };

    public static ArrayList<Deride> getListData(){
        ArrayList<Deride> list = new ArrayList<>();
        for (String[] adata : data) {
            Deride deride = new Deride();
            deride.setId(adata[0]);
            deride.setNo(adata[1]);
            deride.setCustomer(adata[2]);
            deride.setDriver(adata[3]);
            deride.setOngkir(adata[4]);
            deride.setStatus(adata[5]);
            deride.setTanggal(adata[6]);

            list.add(deride);
        }
        return list;
    }
}
