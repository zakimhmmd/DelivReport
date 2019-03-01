package com.example.zaki.delivreport;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeFoodFragment extends Fragment{

    Calendar calendar;
    DatePickerDialog.OnDateSetListener startdate, enddate;
    EditText edt_startdate, edt_enddate;
    Button btn_customer;
    CardView datacustomer;

    public DeFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_de_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_startdate = (EditText) view.findViewById(R.id.edt_startDateDefood);
        edt_enddate = (EditText) view.findViewById(R.id.edt_endDateDefood);
        btn_customer = (Button) view.findViewById(R.id.btn_namacust_defood);
        datacustomer = (CardView) view.findViewById(R.id.data_customerdefood);

        calendar = Calendar.getInstance();
        startdate = new DatePickerDialog.OnDateSetListener() {
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

        btn_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datacustomer.getVisibility() == View.VISIBLE){
                    btn_customer.setBackgroundResource(R.drawable.ic_add_circle);
                    datacustomer.setVisibility(View.GONE) ;
                } else {
                    btn_customer.setBackgroundResource(R.drawable.ic_remove_circle);
                    datacustomer.setVisibility(View.VISIBLE);
                }
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
