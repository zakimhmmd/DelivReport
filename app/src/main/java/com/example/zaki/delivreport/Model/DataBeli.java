package com.example.zaki.delivreport.Model;

import java.util.ArrayList;

public class DataBeli {
    public static String[][] data = new String[][]{
            {"1","1","Nasi Goreng","Tidak ada catatan", "Rp.14.000","Rp.14.000"},
            {"2","1","Mie Goreng","Pedes","Rp.9.000","Rp.9000"}
    };

    public static ArrayList<Beli> getListData(){
        ArrayList<Beli> list = new ArrayList<>();
        for (String[] aData : data){
            Beli beli = new Beli();
            beli.setNo(aData[0]);
            beli.setQty(aData[1]);
            beli.setBarang(aData[2]);
            beli.setCatatan(aData[3]);
            beli.setHarga(aData[4]);
            beli.setSubtotal(aData[5]);

            list.add(beli);
        }
        return list;
    }
}
