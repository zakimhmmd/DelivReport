package com.example.zaki.delivreport;


import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zaki.delivreport.Adapter.ListDefoodAdapter;
import com.example.zaki.delivreport.Model.DefoodResponse;
import com.example.zaki.delivreport.Model.DefoodListData;
import com.example.zaki.delivreport.Model.DefoodStats;
import com.example.zaki.delivreport.Rest.Api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeFoodFragment extends Fragment{

    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener startdate, enddate;
    private EditText edt_startdate, edt_enddate;
    private RecyclerView recyclerView;
    private ListDefoodAdapter listDefoodAdapter = new ListDefoodAdapter(getActivity());

    private RecyclerView.Adapter adapter;
    public DeFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_de_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_startdate = view.findViewById(R.id.edt_startDateDefood);
        edt_enddate = view.findViewById(R.id.edt_endDateDefood);
        Button btn_tanggal = view.findViewById(R.id.btn_terapkandefood);
        recyclerView = view.findViewById(R.id.rv_transaksidefood);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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

        btn_tanggal.setOnClickListener(v -> loadData());
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

        Api.getApiService().getDataDefood(from, to).enqueue(new Callback<DefoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<DefoodResponse> call, @NonNull Response<DefoodResponse> response) {

                if(response.isSuccessful()){

                    ArrayList<DefoodListData> data = null;
                    if (response.body() != null) {
                        data = response.body().getData().getList();
                    }

                    listDefoodAdapter.setListDefood(data);
                    recyclerView.setAdapter(listDefoodAdapter);
                }

            }

            @Override
            public void onFailure(Call<DefoodResponse> call, Throwable t) {

            }
        });
    }


}
