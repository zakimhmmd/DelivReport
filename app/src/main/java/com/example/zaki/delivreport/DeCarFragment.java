package com.example.zaki.delivreport;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.zaki.delivreport.Adapter.ListDeCarAdapter;
import com.example.zaki.delivreport.Model.DecarListData;
import com.example.zaki.delivreport.Model.DecarResponse;
import com.example.zaki.delivreport.Rest.Api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class DeCarFragment extends Fragment {

    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener startdate, enddate;
    private EditText edt_startdate, edt_enddate;
    private RecyclerView recyclerView;
    private Button btn_decar;
    private ListDeCarAdapter listDeCarAdapter = new ListDeCarAdapter(getActivity());
    public DeCarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_de_car, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_startdate = view.findViewById(R.id.edt_startDatedecar);
        edt_enddate = view.findViewById(R.id.edt_endDateDecar);
        recyclerView = view.findViewById(R.id.rv_transaksidecar);
        btn_decar = view.findViewById(R.id.btn_terapkandecar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        calendar = Calendar.getInstance();

        startdate = (view1, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelStart();
        };

        enddate = (view12, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelEnd();
        };

        edt_startdate.setOnClickListener(v -> new DatePickerDialog(getContext(), startdate, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show());

        edt_enddate.setOnClickListener(v -> new DatePickerDialog(getContext(), enddate,calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show());

        btn_decar.setOnClickListener(v -> loadData());
    }

    private void updateLabelStart(){
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        edt_startdate.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void updateLabelEnd(){
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        edt_enddate.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void loadData(){
        String from = edt_startdate.getText().toString();
        String to = edt_enddate.getText().toString();

        Api.getApiService().getDataDecar(from, to).enqueue(new Callback<DecarResponse>() {
            @Override
            public void onResponse(@NonNull Call<DecarResponse> call, @NonNull Response<DecarResponse> response) {
                ArrayList<DecarListData> data = null;
                if (response.body() != null) {
                    data = response.body().getData().getList();
                }
                listDeCarAdapter.setListDecar(data);
                recyclerView.setAdapter(listDeCarAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<DecarResponse> call, Throwable t) {

            }
        });
    }
}
