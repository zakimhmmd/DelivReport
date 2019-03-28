package com.example.zaki.delivreport;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.zaki.delivreport.Model.Dashboard.DashDrivers;
import com.example.zaki.delivreport.Model.Dashboard.DashResponse;
import com.example.zaki.delivreport.Model.Dashboard.DashTrans;
import com.example.zaki.delivreport.Model.Dashboard.DashUsers;
import com.example.zaki.delivreport.Rest.ApiDashboard;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener date;
    private ArrayList<DashTrans> dataTransaksi;
    private ArrayList<DashUsers> dataUsers;
    private ArrayList<DashDrivers> dataDrivers;

    private EditText edtTanggal;
    private Button btnTerapkan;
    private BarChart chartUsers, chartDriver, chartTransaksi;

    public DashboardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chartUsers = view.findViewById(R.id.chart_user);
        chartDriver = view.findViewById(R.id.chart_driver);
        chartTransaksi = view.findViewById(R.id.chart_transaksi);
        btnTerapkan = view.findViewById(R.id.btn_terapkandashboard);
        edtTanggal = view.findViewById(R.id.edt_dateDashboard);

        dataTransaksi = new ArrayList<>();
        dataUsers = new ArrayList<>();
        dataDrivers = new ArrayList<>();

        calendar = Calendar.getInstance();
        date = (view1, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate();
        };

        edtTanggal.setOnClickListener(v -> new DatePickerDialog(getContext(), date, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show());

        btnTerapkan.setOnClickListener(v -> {
            getDataUsers();
            getDataDrivers();
            getDataTransaksi();
        });

        getDataUsers();
        getDataDrivers();
        getDataTransaksi();

    }

    private void getDataUsers(){
        String date = edtTanggal.getText().toString();
        String tanggal;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String curDate = dateFormat.format(calendar.getTime());

        if (date.equals("")){
           tanggal = curDate;
        } else {
            tanggal = date;
        }

        ApiDashboard.getApiService().getDashboardData(tanggal).enqueue(new Callback<DashResponse>() {
            @Override
            public void onResponse(Call<DashResponse> call, Response<DashResponse> response) {
                if (response.isSuccessful()){
                    ArrayList<BarDataSet> dataSets = new ArrayList<>();
                    ArrayList<String> xAxis = new ArrayList<>();

                    if (response.body() != null) {
                        dataUsers = response.body().getData().getUsers();
                    }

                    ArrayList<BarEntry> yVals = new ArrayList<>();
                    for (int i = 0; i < dataUsers.size(); i++){
                        DashUsers dashUsers = dataUsers.get(i);
                        float dataYValue = Float.parseFloat(String.valueOf(dashUsers.getUser()));
                        yVals.add(new BarEntry(dataYValue, i));
                    }

                    for (int i = 0; i <dataUsers.size();i++){
                        DashUsers dashUsers = dataUsers.get(i);
                        String dataXValue = dashUsers.getTanggal();
                        xAxis.add(dataXValue);
                    }

                    BarDataSet dataY = new BarDataSet(yVals, "User");
                    dataY.setColors(ColorTemplate.COLORFUL_COLORS);
                    dataSets.add(dataY);

                    XAxis xposition = chartUsers.getXAxis();
                    xposition.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xposition.setTextSize(7f);
                    xposition.setDrawAxisLine(true);
                    xposition.setDrawGridLines(false);

                    YAxis rightAxis = chartUsers.getAxisRight();
                    rightAxis.setEnabled(false);

                    BarData data = new BarData(xAxis, dataSets);
                    data.setDrawValues(true);
                    data.setValueFormatter(new ChartValueFormatter());
                    data.setValueFormatter(new ChartValueFormatter());

                    chartUsers.setData(data);
                    chartUsers.setDescription(null);
                    chartUsers.animateXY(2000, 2000);
                    chartUsers.invalidate();

                }
            }

            @Override
            public void onFailure(Call<DashResponse> call, Throwable t) {

            }
        });
    }

    private void getDataDrivers(){
        String date = edtTanggal.getText().toString();
        String tanggal;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String curDate = dateFormat.format(calendar.getTime());

        if (date.equals("")){
            tanggal = curDate;
        } else {
            tanggal = date;
        }

        ApiDashboard.getApiService().getDashboardData(tanggal).enqueue(new Callback<DashResponse>() {
            @Override
            public void onResponse(Call<DashResponse> call, Response<DashResponse> response) {
                if (response.isSuccessful()){
                    ArrayList<BarDataSet> dataSets = new ArrayList<>();
                    ArrayList<String> xAxis = new ArrayList<>();

                    if (response.body() != null) {
                        dataDrivers = response.body().getData().getDrivers();
                    }

                    ArrayList<BarEntry> yVals = new ArrayList<>();
                    for (int i = 0; i < dataDrivers.size(); i++){
                        DashDrivers dashDrivers = dataDrivers.get(i);
                        int dataYValue = dashDrivers.getDriver();
                        yVals.add(new BarEntry(dataYValue, i));
                    }

                    for (int i = 0; i <dataDrivers.size();i++){
                        DashDrivers dashDrivers = dataDrivers.get(i);
                        String dataXValue = dashDrivers.getTanggal();
                        xAxis.add(dataXValue);
                    }

                    BarDataSet dataY = new BarDataSet(yVals, "Drivers");
                    dataY.setColors(ColorTemplate.COLORFUL_COLORS);
                    dataSets.add(dataY);

                    XAxis xposition = chartDriver.getXAxis();
                    xposition.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xposition.setTextSize(7f);
                    xposition.setDrawAxisLine(true);
                    xposition.setDrawGridLines(false);

                    YAxis rightAxis = chartDriver.getAxisRight();
                    rightAxis.setEnabled(false);

                    BarData data = new BarData(xAxis, dataSets);
                    data.setDrawValues(true);
                    data.setValueFormatter(new ChartValueFormatter());
                    data.setValueFormatter(new ChartValueFormatter());

                    chartDriver.setData(data);
                    chartDriver.setDescription(null);
                    chartDriver.animateXY(2000, 2000);
                    chartDriver.invalidate();

                }
            }

            @Override
            public void onFailure(Call<DashResponse> call, Throwable t) {

            }
        });
    }

    private void getDataTransaksi(){
        String date = edtTanggal.getText().toString();
        String tanggal;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String curDate = dateFormat.format(calendar.getTime());

        if (date.equals("")){
            tanggal = curDate;
        } else {
            tanggal = date;
        }
        ApiDashboard.getApiService().getDashboardData(tanggal).enqueue(new Callback<DashResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<DashResponse> call, Response<DashResponse> response) {
                if (response.isSuccessful()){
                    ArrayList<BarDataSet> dataSets = new ArrayList<>();
                    ArrayList<String> xAxis = new ArrayList<>();

                    if (response.body() != null) {
                        dataTransaksi = response.body().getData().getTrans();
                    }

                    ArrayList<BarEntry> yVals1 = new ArrayList<>();
                    ArrayList<BarEntry> yVals2 = new ArrayList<>();
                    ArrayList<BarEntry> yVals3 = new ArrayList<>();
                    ArrayList<BarEntry> yVals4 = new ArrayList<>();
                    for (int i = 0; i < dataTransaksi.size(); i++){
                        DashTrans dashTrans = dataTransaksi.get(i);
                        float dataYDeride = Float.parseFloat(String.valueOf(dashTrans.getDeride()));
                        float dataYDefood = Float.parseFloat(String.valueOf(dashTrans.getDefood()));
                        float dataYDeexpress = Float.parseFloat(String.valueOf(dashTrans.getDeexpress()));
                        float dataYDecar = Float.parseFloat(String.valueOf(dashTrans.getDecar()));

                        yVals1.add(new BarEntry(dataYDeride, i));
                        yVals2.add(new BarEntry(dataYDefood, i));
                        yVals3.add(new BarEntry(dataYDeexpress, i));
                        yVals4.add(new BarEntry(dataYDecar, i));
                    }

                    for (int i = 0; i <dataTransaksi.size();i++){
                        DashTrans dashTrans = dataTransaksi.get(i);
                        String dataXValue = dashTrans.getTanggal();
                        xAxis.add(dataXValue);
                    }

                    BarDataSet dataYDeride = new BarDataSet(yVals1, "Deride");
                    dataYDeride.setColors(Collections.singletonList(Color.BLUE));
                    BarDataSet dataYDefood = new BarDataSet(yVals2, "Defood");
                    dataYDefood.setColors(Collections.singletonList(Color.RED));
                    BarDataSet dataYDeexpress = new BarDataSet(yVals3, "Deexpress");
                    dataYDeexpress.setColors(Collections.singletonList(Color.GREEN));
                    BarDataSet dataYDecar = new BarDataSet(yVals4, "Decar");
                    dataYDecar.setColors(Collections.singletonList(Color.MAGENTA));

                    dataSets.add(dataYDeride);
                    dataSets.add(dataYDefood);
                    dataSets.add(dataYDeexpress);
                    dataSets.add(dataYDecar);

                    XAxis xposition = chartTransaksi.getXAxis();
                    xposition.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xposition.setTextSize(7f);
                    xposition.setDrawAxisLine(true);
                    xposition.setDrawGridLines(false);

                    YAxis rightAxis = chartTransaksi.getAxisRight();
                    rightAxis.setEnabled(false);

                    BarData data = new BarData(xAxis, dataSets);
                    data.setDrawValues(true);
                    data.setValueFormatter(new ChartValueFormatter());
                    data.setValueFormatter(new ChartValueFormatter());

                    chartTransaksi.setData(data);
                    chartTransaksi.setDescription(null);
                    chartTransaksi.animateXY(2000, 2000);
                    chartTransaksi.invalidate();
                }
            }

            @Override
            public void onFailure(Call<DashResponse> call, Throwable t) {

            }
        });
    }


    private void updateDate(){
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        edtTanggal.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
