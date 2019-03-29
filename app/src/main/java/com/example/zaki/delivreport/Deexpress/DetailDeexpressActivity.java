package com.example.zaki.delivreport.Deexpress;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zaki.delivreport.Model.Deexpress.DetailDriver;
import com.example.zaki.delivreport.Model.Deexpress.DetailNota;
import com.example.zaki.delivreport.Model.Deexpress.DetailResponse;
import com.example.zaki.delivreport.R;
import com.example.zaki.delivreport.Rest.ApiDetail;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.zaki.delivreport.Defood.DeFoodFragment.EXTRA_ID;

public class DetailDeexpressActivity extends AppCompatActivity {

    private ImageView fotoDriver, fotoNota;
    private TextView idDeride, pickAddress, destAddress, jarak, status, tanggalPesan, waktuSelesai, rentangWaktu, ongkir,
            diskon, metodeBayar, kodeVoucher, alasanCancel, kota, namaUser, emailUser, noHpUser, namaDriver, noHpDriver, jenisKendaraan,
            namaPengirim, nohpPengirim, namaPenerima, nohpPenerima, deskripsiPaket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_deexpress);

        fotoDriver = findViewById(R.id.id_fotoDriverdeexpress);
        fotoNota = findViewById(R.id.id_fotonotadeexpress);
        idDeride = findViewById(R.id.id_pesanandeexpress);
        pickAddress = findViewById(R.id.id_alamat_jemput_deexpress);
        destAddress = findViewById(R.id.id_alamat_tujuan_deexpress);
        jarak = findViewById(R.id.id_jarak_deexpress);
        status = findViewById(R.id.id_detailstatus_deexpress);
        tanggalPesan = findViewById(R.id.id_waktupesan_deexpress);
        waktuSelesai = findViewById(R.id.id_waktuselesai_deexpress);
        rentangWaktu = findViewById(R.id.id_rentangwaktu_deexpress);
        ongkir = findViewById(R.id.id_detailongkir_deexpress);
        diskon = findViewById(R.id.id_diskon_deexpress);
        metodeBayar = findViewById(R.id.id_metodebayar_Deexpress);
        kodeVoucher = findViewById(R.id.id_kodevoucher_Deexpress);
        alasanCancel = findViewById(R.id.id_alasancancel_deexpress);
        kota = findViewById(R.id.id_kota_deexpress);
        namaUser = findViewById(R.id.id_namapengguna_Deexpress);
        emailUser = findViewById(R.id.id_emailpelanggan_Deexpress);
        noHpUser = findViewById(R.id.id_nohppenguna_deexpress);
        namaDriver = findViewById(R.id.id_namadriver_deexpress);
        noHpDriver = findViewById(R.id.id_nohpdriver_Deexpress);
        jenisKendaraan = findViewById(R.id.id_jeniskendaraan_Deexpress);
        namaPengirim = findViewById(R.id.id_namapengirim_Deexpress);
        nohpPengirim = findViewById(R.id.id_nohppengirim_Deexpress);
        namaPenerima = findViewById(R.id.id_namapenerima_Deexpress);
        nohpPenerima = findViewById(R.id.id_nohppenerima_Deexpress);
        deskripsiPaket = findViewById(R.id.id_deskripsipaket);

        loadData();

    }

    private void loadData(){
        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_ID, 0);
        String idData = String.valueOf(id);
        idDeride.setText("Data Pesanan ID : "+idData);

        ApiDetail.getApiService().getDetailDeexpress(idData).enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                    if (response.body() != null){
                        try{
                            DetailDriver imgDriver = response.body().getData().getDriver();
                            DetailNota imgNota = response.body().getData().getNota();
                            String startDate = formatDate(response.body().getData().getStartTime());
                            String endDate = formatDate(response.body().getData().getEndTime());

                            pickAddress.setText(response.body().getData().getPickAddress());
                            destAddress.setText(response.body().getData().getDestAddress());
                            jarak.setText(response.body().getData().getDistance());
                            status.setText(response.body().getData().getStatus());
                            tanggalPesan.setText(startDate);
                            waktuSelesai.setText(endDate);
                            rentangWaktu.setText(rentangWaktu(startDate, endDate));
                            ongkir.setText(response.body().getData().getPrice());
                            diskon.setText(response.body().getData().getDiscount());
                            metodeBayar.setText(response.body().getData().getPayment());
                            kodeVoucher.setText(response.body().getData().getVoucher());
                            alasanCancel.setText(response.body().getData().getReasonCancel());
                            kota.setText(response.body().getData().getKota().getCityName());
                            namaPengirim.setText(response.body().getData().getSenderName());
                            nohpPengirim.setText(response.body().getData().getSenderNumber());
                            namaPenerima.setText(response.body().getData().getRecipientName());
                            nohpPenerima.setText(response.body().getData().getRecipientNumber());
                            deskripsiPaket.setText(response.body().getData().getPackageInfo());
                            namaUser.setText(response.body().getData().getUser().getName());
                            emailUser.setText(response.body().getData().getUser().getEmail());
                            noHpUser.setText(response.body().getData().getUser().getPhone());
                            namaDriver.setText(response.body().getData().getDriver().getName());
                            noHpDriver.setText(response.body().getData().getDriver().getPhone());
                            jenisKendaraan.setText(response.body().getData().getVehicle());
                            if (imgDriver == null){
                                Picasso.with(DetailDeexpressActivity.this).load(R.drawable.ic_person).into(fotoDriver);
                            } else {
                                Picasso.with(DetailDeexpressActivity.this).load(imgDriver.getSrc().getImage()).into(fotoDriver);
                            }
                            if (imgNota == null){
                                Picasso.with(DetailDeexpressActivity.this).load(R.drawable.ic_image).into(fotoNota);
                            } else {
                                Picasso.with(DetailDeexpressActivity.this).load(imgNota.getImage()).into(fotoNota);
                            }

                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                }


            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {

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

    private String rentangWaktu(String start, String end){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        String rentang = "";
        try {
            Date startDate = simpleDateFormat.parse(formatTime(start));
            Date endDate = simpleDateFormat.parse(formatTime(end));

            long difference = endDate.getTime() - startDate.getTime();
            int hours = (int) (difference/(1000 * 60 * 60));
            int minute = (int) ((difference/(1000*60))%60);

            rentang = hours+" Jam "+minute+" Menit";
        } catch (ParseException e){
            e.printStackTrace();
        }
        return rentang;
    }

    private static String formatTime(String timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        Date date = new Date();
        try {
            date = sdf.parse(timestamp);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sdfResult.format(date);
    }
}
