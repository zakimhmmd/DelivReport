package com.example.zaki.delivreport.Depayment;


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

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.zaki.delivreport.Adapter.ListDepayAdapter;
import com.example.zaki.delivreport.Model.Defood.DefoodListData;
import com.example.zaki.delivreport.Model.Depayment.DepayList;
import com.example.zaki.delivreport.Model.Depayment.DepayResponse;
import com.example.zaki.delivreport.Model.Depayment.DepayData;
import com.example.zaki.delivreport.R;
import com.example.zaki.delivreport.Rest.Api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DePaymentFragment extends Fragment {

    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener startdate, enddate;
    private EditText edt_startdate, edt_enddate, edtCari;
    private RecyclerView recyclerView;
    private Button btnTanggal;

    private ListDepayAdapter listDepayAdapter = new ListDepayAdapter(getActivity());
    private ArrayList<DepayList> data;

    public DePaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_de_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_startdate = view.findViewById(R.id.edt_startDatedepay);
        edt_enddate = view.findViewById(R.id.edt_endDateDepay);
        edtCari = view.findViewById(R.id.edt_searchkeydepay);
        recyclerView = view.findViewById(R.id.rv_transaksidepay);
        btnTanggal = view.findViewById(R.id.btn_terapkandepay);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);

        calendar = Calendar.getInstance();

        startdate = (view12, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelStart();
        };

        enddate = (view1, year, month, dayOfMonth) -> {
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

        btnTanggal.setOnClickListener(v -> {
            loadData();
        });

        loadData();
        searchData();

    }

    private void searchData(){
        edtCari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listDepayAdapter.setListDepay(linearSearch(data, s.toString()));
            }
        });
    }

    private ArrayList<DepayList> linearSearch(ArrayList<DepayList> list, String key){
        ArrayList<DepayList> result = new ArrayList<>();
        if(TextUtils.isEmpty(key)){
            return list;
        }
        for(DepayList data :list){
            if(String.valueOf(data.getId()).contains(key)){
                result.add(data);
            }
        }
        return result;
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

        Api.getApiService().getDataDepay(startDate, endDate).enqueue(new Callback<DepayResponse>() {
            @Override
            public void onResponse(Call<DepayResponse> call, Response<DepayResponse> response) {
                if (response.isSuccessful()){
                    data = null;
                    if (response.body() != null){
                        data = response.body().getData().getList();
                    }
                    listDepayAdapter.setListDepay(data);
                    recyclerView.setAdapter(listDepayAdapter);
                }
            }

            @Override
            public void onFailure(Call<DepayResponse> call, Throwable t) {

            }
        });
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

}
