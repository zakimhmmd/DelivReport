package com.example.zaki.delivreport.Deride;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class DetailDerideActivity extends AppCompatActivity {

    private ImageView fotoDriver;
    private TextView idDeride, pickAddress, destAddress, jarak, status, tanggalPesan, waktuSelesai, rentangWaktu, ongkir,
    potongan, metodeBayar, kodeVoucher, alasanCancel, kota, namaUser, emailUser, noHpUser, namaDriver, noHpDriver, jenisKendaraan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_deride);

        fotoDriver = findViewById(R.id.id_fotoDriver);
        idDeride = findViewById(R.id.id_pesananderide);
        pickAddress = findViewById(R.id.id_alamat_jemput_deride);
        destAddress = findViewById(R.id.id_alamat_tujuan_deride);
        jarak = findViewById(R.id.id_jarak_deride);
        status = findViewById(R.id.id_detailstatus_deride);
        tanggalPesan = findViewById(R.id.id_waktupesan_deride);
        waktuSelesai = findViewById(R.id.id_waktuselesai_deride);
        rentangWaktu = findViewById(R.id.id_rentangwaktu_deride);
        ongkir = findViewById(R.id.id_detailongkir_deride);
        potongan = findViewById(R.id.id_diskon_deride);
        metodeBayar = findViewById(R.id.id_metodebayar_Deride);
        kodeVoucher = findViewById(R.id.id_kodevoucher_Deride);
        alasanCancel = findViewById(R.id.id_alasancancel_deride);
        kota = findViewById(R.id.id_kota_deride);
        namaUser = findViewById(R.id.id_namapengguna_Deride);
        emailUser = findViewById(R.id.id_emailpelanggan_Deride);
        noHpUser = findViewById(R.id.id_nohppenguna_deride);
        namaDriver = findViewById(R.id.id_namadriver_deride);
        noHpDriver = findViewById(R.id.id_nohpdriver_Deride);
        jenisKendaraan = findViewById(R.id.id_keniskendaraan_Deride);

        loadData();
    }

    private void loadData(){
        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_ID, 0);
        String idData = String.valueOf(id);
        idDeride.setText("Detail Pesanan ID : "+idData);

        ApiDetail.getApiService().getDetailDeride(idData).enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        try {
                            String foto = response.body().getData().getDriver().getSrc().getImage();

                            pickAddress.setText(response.body().getData().getPickAddress());
                            destAddress.setText(response.body().getData().getDestAddress());
                            jarak.setText(response.body().getData().getDistance());
                            status.setText(response.body().getData().getStatus());
                            tanggalPesan.setText(formatDate(response.body().getData().getStartTime()));
                            waktuSelesai.setText(formatDate(response.body().getData().getEndTime()));
                            rentangWaktu.setText("");
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
                            Picasso.with(DetailDerideActivity.this).load(foto).into(fotoDriver);
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                Toast.makeText(DetailDerideActivity.this, ""+t, Toast.LENGTH_LONG).show();

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
}
