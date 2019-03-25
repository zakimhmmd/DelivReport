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

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zaki.delivreport.Adapter.ListDeCarAdapter;
import com.example.zaki.delivreport.Adapter.ListDeexpresAdapter;
import com.example.zaki.delivreport.Model.DecarListData;
import com.example.zaki.delivreport.Model.DeexpresData;
import com.example.zaki.delivreport.Model.DeexpressListData;
import com.example.zaki.delivreport.Model.DeexpressResponse;
import com.example.zaki.delivreport.Rest.Api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeExpressFragment extends Fragment {

    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener startdate, enddate;
    private EditText edt_startdate, edt_enddate, edt_search;
    private TextView complete, cancel, booking;
    private RecyclerView recyclerView;
    private Button btn_deexpress;
    private ListDeexpresAdapter listDeexpresAdapter = new ListDeexpresAdapter(getActivity());
    private ArrayList<DeexpressListData> data;
    public DeExpressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_de_express, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_startdate = view.findViewById(R.id.edt_startDatedeexpres);
        edt_enddate = view.findViewById(R.id.edt_endDateDedeexpres);
        edt_search = view.findViewById(R.id.edt_searchkeydeexpres);
        recyclerView = view.findViewById(R.id.rv_transaksideexpres);
        btn_deexpress = view.findViewById(R.id.btn_terapkandeexpres);
        complete = view.findViewById(R.id.id_completedeexpress);
        cancel = view.findViewById(R.id.id_canceldeexpres);
        booking = view.findViewById(R.id.id_bookingdeexpres);

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

        btn_deexpress.setOnClickListener(v -> loadData());

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

        Api.getApiService().getDataDeexpress(startDate, endDate).enqueue(new Callback<DeexpressResponse>() {
            @Override
            public void onResponse(@NonNull Call<DeexpressResponse> call, @NonNull Response<DeexpressResponse> response) {
                data = null;
                if (response.body() != null) {
                    data = response.body().getData().getList();
                    complete.setText(String.valueOf(response.body().getData().getStats().getComplete()));
                    cancel.setText(String.valueOf(response.body().getData().getStats().getCancel()));
                    booking.setText(String.valueOf(response.body().getData().getStats().getBooking()));
                }
                listDeexpresAdapter.setListDeexpress(data);
                recyclerView.setAdapter(listDeexpresAdapter);

            }

            @Override
            public void onFailure(@NonNull Call<DeexpressResponse> call, Throwable t) {

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
                listDeexpresAdapter.setListDeexpress(linearSearch(data, s.toString()));
            }
        });
    }

    private ArrayList<DeexpressListData> linearSearch(ArrayList<DeexpressListData> list, String key){
        ArrayList<DeexpressListData> result = new ArrayList<>();
        if(TextUtils.isEmpty(key)){
            return list;
        }
        for(DeexpressListData data :list){
            if(String.valueOf(data.getId()).contains(key)){
                result.add(data);
            }
        }
        return result;
    }
}
