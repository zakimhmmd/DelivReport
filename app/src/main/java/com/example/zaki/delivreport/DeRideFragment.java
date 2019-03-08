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

import com.example.zaki.delivreport.Adapter.ListDerideAdapter;
import com.example.zaki.delivreport.Model.Deride;
import com.example.zaki.delivreport.Model.DerideData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeRideFragment extends Fragment {

    Calendar calendar;
    DatePickerDialog.OnDateSetListener startdate, enddate;
    EditText edt_startdate, edt_enddate;
    RecyclerView recyclerView;
    private ArrayList<Deride> list = new ArrayList<>();

    public DeRideFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_de_ride, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_startdate = (EditText) view.findViewById(R.id.edt_startDatederide);
        edt_enddate = (EditText) view.findViewById(R.id.edt_endDateDeride);
        recyclerView = view.findViewById(R.id.rv_transaksideride);

        recyclerView.setHasFixedSize(true);
        list.addAll(DerideData.getListData());
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
        ListDerideAdapter listDerideAdapter = new ListDerideAdapter(getActivity());
        listDerideAdapter.setListDeride(list);
        recyclerView.setAdapter(listDerideAdapter);
    }
}
