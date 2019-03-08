package com.example.zaki.delivreport.Model;

import java.util.ArrayList;

public class DefoodData {
    public static String[][] data = new String[][]{
            {"#4444", "4444", "Zaki", "Riza", "Ayam Nelongso", "Rp.10.000", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Cak Midi", "Rp. 9.000", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"},
            {"#4444", "4444", "Zaki", "Riza", "Ayam Nelongso", "Rp.10.000", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Cak Midi", "Rp. 9.000", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"},
            {"#4444", "4444", "Zaki", "Riza", "Ayam Nelongso", "Rp.10.000", "Rp.4.000", "Complete", "Senin, 4 Maret 2019 16:01:01"},
            {"#5555", "5555", "Damar", "Rutab", "Cak Midi", "Rp. 9.000", "Rp. 5.000", "Cancel", "Selasa, 26 Februari 2019 14:23:54"}
    };

    public static ArrayList<Defood> getListData(){
        ArrayList<Defood> list = new ArrayList<>();
        for (String[] adata : data){
            Defood defood = new Defood();
            defood.setId(adata[0]);
            defood.setNo(adata[1]);
            defood.setCustomer(adata[2]);
            defood.setDriver(adata[3]);
            defood.setRestoran(adata[4]);
            defood.setHarga(adata[5]);
            defood.setOngkir(adata[6]);
            defood.setStatus(adata[7]);
            defood.setTanggal(adata[8]);

            list.add(defood);
        }
        return list;
    }
}
