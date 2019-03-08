package com.example.zaki.delivreport;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.zaki.delivreport.Adapter.ListDeexpresAdapter;
import com.example.zaki.delivreport.Model.Deexpres;
import com.example.zaki.delivreport.Model.DeexpresData;
import com.example.zaki.delivreport.Model.DerideData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeExpressFragment extends Fragment {

    Calendar calendar;
    DatePickerDialog.OnDateSetListener startdate, enddate;
    EditText edt_startdate, edt_enddate;
    RecyclerView recyclerView;
    private ArrayList<Deexpres> list = new ArrayList<>();

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

        edt_startdate = (EditText) view.findViewById(R.id.edt_startDatedeexpres);
        edt_enddate = (EditText) view.findViewById(R.id.edt_endDateDedeexpres);
        recyclerView = view.findViewById(R.id.rv_transaksideexpres);

        recyclerView.setHasFixedSize(true);
        list.addAll(DeexpresData.getListData());
        showRecyclerList();

        calendar = Calendar.getInstance();

        startdate = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

        enddate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }
        };

        edt_startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), startdate, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edt_enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), enddate,calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
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

    private void showRecyclerList(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListDeexpresAdapter listDeexpresAdapter = new ListDeexpresAdapter(getActivity());
        listDeexpresAdapter.setListDeexpress(list);
        recyclerView.setAdapter(listDeexpresAdapter);
    }
}
