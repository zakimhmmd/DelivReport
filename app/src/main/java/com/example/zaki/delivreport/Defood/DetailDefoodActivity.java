package com.example.zaki.delivreport.Defood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.zaki.delivreport.Adapter.ListDetaiDefoodAdapter;
import com.example.zaki.delivreport.Model.Defood.DetailData;
import com.example.zaki.delivreport.Model.Defood.DetailKeranjang;
import com.example.zaki.delivreport.Model.Defood.DetailResponse;
import com.example.zaki.delivreport.R;
import com.example.zaki.delivreport.Rest.ApiDetail;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.zaki.delivreport.Defood.DeFoodFragment.EXTRA_ID;

public class DetailDefoodActivity extends AppCompatActivity {
    private ListDetaiDefoodAdapter adapter = new ListDetaiDefoodAdapter(this);
    private ArrayList<DetailKeranjang> listData = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView notaPesan;
    private TextView idDefood, alamatKirim, catatan, jarak, status, tanggalPesanan, waktuSelesai, rentangWaktu,
            kota, metodeBayar, kodeVoucher, alasanCancel, namaUser, emailUser, namaRestoran, noHpRestoran,
            alamatRestoran, namaDriver, noHpDriver, totalQty, totalHarga, hargaFinal, ongkosKirim, diskon, totalFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaildefood);
        recyclerView = findViewById(R.id.rv_databeli_defood);
        idDefood = findViewById(R.id.id_pesanandefood);
        alamatKirim = findViewById(R.id.id_alamat_kirim_defood);
        catatan = findViewById(R.id.id_catatan_defood);
        jarak = findViewById(R.id.id_jarak_defood);
        status = findViewById(R.id.id_detailstatus_defood);
        tanggalPesanan = findViewById(R.id.id_waktupesan_defood);
        waktuSelesai = findViewById(R.id.id_waktuselesai_defood);
        kodeVoucher = findViewById(R.id.id_kodevoucher_Defood);
        kota = findViewById(R.id.id_kotapesan_defood);
        metodeBayar = findViewById(R.id.id_metodebayar_Defood);
        alasanCancel = findViewById(R.id.id_alasancancel_defood);
        namaUser = findViewById(R.id.id_namapelanggan_Defood);
        emailUser = findViewById(R.id.id_emailpelanggan_Defood);
        namaRestoran = findViewById(R.id.id_namarestoran);
        noHpRestoran = findViewById(R.id.id_nohprestoran);
        alamatRestoran = findViewById(R.id.id_alamatrestoran);
        namaDriver = findViewById(R.id.id_namadriver_defood);
        noHpDriver = findViewById(R.id.id_nohpdriver_Defood);
        totalQty = findViewById(R.id.id_totalqty_defood);
        totalHarga = findViewById(R.id.id_totalharga_defood);
        hargaFinal = findViewById(R.id.id_hargafinal_defood);
        ongkosKirim = findViewById(R.id.id_detailongkir_defood);
        diskon = findViewById(R.id.id_diskon_defood);
        totalFinal = findViewById(R.id.id_totalfinal_defood);
        notaPesan = findViewById(R.id.id_imgnota_defood);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        loadData();
    }

    private void loadData(){
        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_ID, 0);
        String idData = String.valueOf(id);
        idDefood.setText("Detail Pesanan ID : "+idData);

        ApiDetail.getApiService().getDetailDefood(idData).enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {

                if (response.isSuccessful()){
                    if (response.body() != null) {
                        try {

                            String image = response.body().getData().getNota().getImage();
                            double harga = response.body().getData().getHargaFinal();
                            double ongkir = response.body().getData().getOngkir();
                            double potongan = response.body().getData().getDiskon();
                            double total = (harga + ongkir) - potongan;
                            String startDate = formatDate(response.body().getData().getCreatedAt());
                            String endDate = formatDate(response.body().getData().getUpdatedAt());

                            alamatKirim.setText(response.body().getData().getAlamatPengiriman());
                            catatan.setText(response.body().getData().getCatatan());
                            jarak.setText(String.valueOf(response.body().getData().getJarak() + " Km"));
                            status.setText(response.body().getData().getStatus());
                            tanggalPesanan.setText(startDate);
                            waktuSelesai.setText(endDate);
//                        rentangWaktu.setText(rentangWaktu(startDate, endDate));
                            kota.setText(response.body().getData().getKota().getCityName());
                            metodeBayar.setText(response.body().getData().getPayment());
                            kodeVoucher.setText(response.body().getData().getKode());
                            alasanCancel.setText(response.body().getData().getAlasanCancel());
                            if (image != null){
                                namaUser.setText(response.body().getData().getUser().getName());
                            }
                            Picasso.with(DetailDefoodActivity.this).load(image).into(notaPesan);
                            emailUser.setText(response.body().getData().getUser().getEmail());
                            namaRestoran.setText(response.body().getData().getRestoran().getName());
                            noHpRestoran.setText(response.body().getData().getRestoran().getPhone());
                            alamatRestoran.setText(response.body().getData().getRestoran().getAlamat());
                            namaDriver.setText(response.body().getData().getDriver().getName());
                            noHpDriver.setText(response.body().getData().getDriver().getPhone());
//                        lokasidriver
                            listData = response.body().getData().getCart().getKeranjang();
                            adapter.setListData(listData);
                            recyclerView.setAdapter(adapter);
                            totalQty.setText(String.valueOf(response.body().getData().getCart().getKeranjang().size()));
                            totalHarga.setText(String.valueOf("Rp. " + response.body().getData().getHargaEstimasi()));
                            hargaFinal.setText(String.valueOf("Rp. " + harga));
                            ongkosKirim.setText(String.valueOf("Rp. " + ongkir));
                            diskon.setText(String.valueOf("Rp. " + potongan));
                            totalFinal.setText(String.valueOf("Rp. " + total));
                        } catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                Log.e("APIERROR", t.getMessage());
            }
        });
    }

    private static String formatDate(String timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdfResult = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault());

        Date date = new Date();
        try {
            date = sdf.parse(timestamp);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sdfResult.format(date);
    }

    private static String formatTime(String timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.getDefault());

        Date date = new Date();
        try {
            date = sdf.parse(timestamp);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sdfResult.format(date);
    }

    private String rentangWaktu(String start, String end){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String rentang = "";
        try {
            Date startDate = simpleDateFormat.parse(formatTime(start));
            Date endDate = simpleDateFormat.parse(formatTime(end));
            long difference = endDate.getTime() - startDate.getTime();
            if(difference<0)
            {
                Date dateMax = simpleDateFormat.parse("24:00");
                Date dateMin = simpleDateFormat.parse("00:00");
                difference=(dateMax.getTime() -startDate.getTime() )+(endDate.getTime()-dateMin.getTime());
            }
            int days = (int) (difference / (1000*60*60*24));
            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
            rentang = hours+" Jam "+min+" Menit"; 
            
        } catch (ParseException e){
            e.printStackTrace();
        }
        return rentang;
    }
}
