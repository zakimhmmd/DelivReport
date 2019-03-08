package com.example.zaki.delivreport.Model;

import java.util.ArrayList;

public class DepayData {
    public static String[][] data = new String[][]{
            {"#59", "1","Zaki","PLN Prepaid","cancel","Rp. 500.250","Rp. 503.000","UNKNOWN MTI / JENIS PESAN TIDAK DISUPPORT OLEH BILLER","Rabu, 06 Mar 2019 00:11:31"},
            {"#79", "2","Damar","PLN Prepaid","cancel","Rp. 500.250","Rp. 503.000","UNKNOWN MTI / JENIS PESAN TIDAK DISUPPORT OLEH BILLER","Rabu, 06 Mar 2019 00:11:31"},
            {"#21", "3","Dian","PLN Prepaid","cancel","Rp. 500.250","Rp. 503.000","UNKNOWN MTI / JENIS PESAN TIDAK DISUPPORT OLEH BILLER","Rabu, 06 Mar 2019 00:11:31"}
    };

    public static ArrayList<Depay> getlistData(){
        ArrayList<Depay> list = new ArrayList<>();
        for (String[] adata : data){
            Depay depay = new Depay();
            depay.setId(adata[0]);
            depay.setNo(adata[1]);
            depay.setUser(adata[2]);
            depay.setLayanan(adata[3]);
            depay.setStatus(adata[4]);
            depay.setBuy(adata[5]);
            depay.setSell(adata[6]);
            depay.setDeskripsi(adata[7]);
            depay.setTanggal(adata[8]);

            list.add(depay);
        }
        return list;
    }
}
