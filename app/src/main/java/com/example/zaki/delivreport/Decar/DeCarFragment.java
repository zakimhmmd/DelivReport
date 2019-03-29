package com.example.zaki.delivreport.Decar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zaki.delivreport.Adapter.ListDeCarAdapter;
import com.example.zaki.delivreport.Model.Decar.DecarListData;
import com.example.zaki.delivreport.Model.Decar.DecarResponse;
import com.example.zaki.delivreport.R;
import com.example.zaki.delivreport.Rest.Api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class DeCarFragment extends Fragment implements ListDeCarAdapter.OnItemClickListener {

    public static final String EXTRA_ID = "id";

    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener startdate, enddate;
    private EditText edt_startdate, edt_enddate, edt_search;
    private TextView complete, cancel, booking;
    private RecyclerView recyclerView;
    private Button btn_decar;
    private ProgressBar spiner;

    private ListDeCarAdapter listDeCarAdapter = new ListDeCarAdapter(getActivity());
    private ArrayList<DecarListData> data;
    private ArrayList<DecarListData> searchResult;
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
        edt_search = view.findViewById(R.id.edt_searchkeydecar);
        recyclerView = view.findViewById(R.id.rv_transaksidecar);
        btn_decar = view.findViewById(R.id.btn_terapkandecar);
        complete = view.findViewById(R.id.id_completedecar);
        cancel = view.findViewById(R.id.id_canceldecar);
        booking = view.findViewById(R.id.id_bookingdecar);
        spiner = view.findViewById(R.id.progressBardecar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);

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

        btn_decar.setOnClickListener(v -> {
            spiner.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            loadData();
        });

        loadData();
        searchData();
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
        String startDate, endDate;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String curDate = dateFormat.format(calendar.getTime());

        if (from.equals("") || to.equals("")){
            edt_startdate.setText(curDate);
            edt_enddate.setText(curDate);
            startDate = curDate;
            endDate = curDate;
        } else {
            startDate = from;
            endDate = to;
        }
        Api.getApiService().getDataDecar(startDate, endDate).enqueue(new Callback<DecarResponse>() {
            @Override
            public void onResponse(@NonNull Call<DecarResponse> call, @NonNull Response<DecarResponse> response) {
                spiner.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                data = null;
                if (response.body() != null) {
                    listDeCarAdapter.setOnItemClickListener(DeCarFragment.this);
                    data = response.body().getData().getList();
                    complete.setText(String.valueOf(response.body().getData().getStats().getComplete()));
                    cancel.setText(String.valueOf(response.body().getData().getStats().getCancel()));
                    booking.setText(String.valueOf(response.body().getData().getStats().getBooking()));

                }
                listDeCarAdapter.setListDecar(data);
                recyclerView.setAdapter(listDeCarAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<DecarResponse> call, Throwable t) {

            }
        });
    }

    private void searchData(){
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listDeCarAdapter.setListDecar(linearSearch(data, s.toString()));
            }
        });
    }

    private ArrayList<DecarListData> linearSearch(ArrayList<DecarListData> list, String key){
        ArrayList<DecarListData> result = new ArrayList<>();
        if(TextUtils.isEmpty(key)){
            return list;
        }
        for(DecarListData data :list){
            if(String.valueOf(data.getId()).contains(key) || String.valueOf(data.getNamaDriver()).contains(key)
                    || String.valueOf(data.getNamaUser()).contains(key)){
                result.add(data);
            }
        }
        return result;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), DetailDecarActivity.class);
        DecarListData clickedItem = data.get(position);

        intent.putExtra(EXTRA_ID, clickedItem.getId());

        startActivity(intent);
    }
}
