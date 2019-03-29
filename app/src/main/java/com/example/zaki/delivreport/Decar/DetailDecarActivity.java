package com.example.zaki.delivreport.Decar;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zaki.delivreport.Model.Deride.DetailResponse;
import com.example.zaki.delivreport.R;
import com.example.zaki.delivreport.Rest.ApiDetail;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.zaki.delivreport.Defood.DeFoodFragment.EXTRA_ID;

public class DetailDecarActivity extends AppCompatActivity {

    private ImageView fotoDriver;
    private TextView idDecar, pickAddress, destAddress, jarak, status, tanggalPesan, waktuSelesai, rentangWaktu, ongkir,
            potongan, metodeBayar, kodeVoucher, alasanCancel, kota, namaUser, emailUser, noHpUser, namaDriver, noHpDriver, jenisKendaraan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_decar);

        fotoDriver = findViewById(R.id.id_fotoDriverDecar);
        idDecar = findViewById(R.id.id_pesanandecar);
        pickAddress = findViewById(R.id.id_alamat_jemput_decar);
        destAddress = findViewById(R.id.id_alamat_tujuan_decar);
        jarak = findViewById(R.id.id_jarak_decar);
        status = findViewById(R.id.id_detailstatus_decar);
        tanggalPesan = findViewById(R.id.id_waktupesan_decar);
        waktuSelesai = findViewById(R.id.id_waktuselesai_decar);
        rentangWaktu = findViewById(R.id.id_rentangwaktu_decar);
        ongkir = findViewById(R.id.id_detailongkir_decar);
        potongan = findViewById(R.id.id_diskon_decar);
        metodeBayar = findViewById(R.id.id_metodebayar_Decar);
        kodeVoucher = findViewById(R.id.id_kodevoucher_Decar);
        alasanCancel = findViewById(R.id.id_alasancancel_decar);
        kota = findViewById(R.id.id_kota_decar);
        namaUser = findViewById(R.id.id_namapengguna_Decar);
        emailUser = findViewById(R.id.id_emailpelanggan_Decar);
        noHpUser = findViewById(R.id.id_nohppenguna_decar);
        namaDriver = findViewById(R.id.id_namadriver_decar);
        noHpDriver = findViewById(R.id.id_nohpdriver_Decar);
        jenisKendaraan = findViewById(R.id.id_jeniskendaraan_Decar);

        loadData();

    }

    private void loadData(){

        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_ID, 0);
        String idData = String.valueOf(id);
        idDecar.setText("Detail Pesanan ID : "+idData);

        ApiDetail.getApiService().getDetailDeride(idData).enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        try{
                            String foto = response.body().getData().getDriver().getSrc().getImage();
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
                            potongan.setText(response.body().getData().getDiscount());
                            metodeBayar.setText(response.body().getData().getPayment());
                            kodeVoucher.setText(response.body().getData().getVoucher());
                            alasanCancel.setText(response.body().getData().getReasonCancel());
                            kota.setText(response.body().getData().getKota().getCityName());
                            namaUser.setText(response.body().getData().getUser().getName());
                            emailUser.setText(response.body().getData().getUser().getEmail());
                            noHpUser.setText(response.body().getData().getUser().getPhone());
                            namaDriver.setText(response.body().getData().getDriver().getName());
                            noHpDriver.setText(response.body().getData().getDriver().getPhone());
                            jenisKendaraan.setText(response.body().getData().getVehicle());
                            Picasso.with(DetailDecarActivity.this).load(foto).into(fotoDriver);
                        } catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {

            }
        });

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
}
