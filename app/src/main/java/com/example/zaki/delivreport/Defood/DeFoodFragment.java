package com.example.zaki.delivreport.Defood;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zaki.delivreport.Adapter.ListDefoodAdapter;
import com.example.zaki.delivreport.Model.Defood.DefoodResponse;
import com.example.zaki.delivreport.Model.Defood.DefoodListData;
import com.example.zaki.delivreport.R;
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
public class DeFoodFragment extends Fragment implements ListDefoodAdapter.OnItemClickListener {

    public static final String EXTRA_ID = "id";

    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener startdate, enddate;
    private EditText edt_startdate, edt_enddate, edt_cari;
    private TextView complete, cancel, booking;
    private Button btn_tanggal;
    private RecyclerView recyclerView;

    private ListDefoodAdapter listDefoodAdapter = new ListDefoodAdapter(getActivity());
    private ArrayList<DefoodListData> data;


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
        edt_cari = view.findViewById(R.id.edt_searchkeydefood);
        btn_tanggal = view.findViewById(R.id.btn_terapkandefood);
        recyclerView = view.findViewById(R.id.rv_transaksidefood);
        complete = view.findViewById(R.id.id_completedefood);
        cancel = view.findViewById(R.id.id_canceldefood);
        booking = view.findViewById(R.id.id_bookingdefood);

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

        btn_tanggal.setOnClickListener(v -> loadData());

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

        Api.getApiService().getDataDefood(startDate, endDate).enqueue(new Callback<DefoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<DefoodResponse> call, @NonNull Response<DefoodResponse> response) {

                if(response.isSuccessful()){
                    data = null;
                    if (response.body() != null) {
                        data = response.body().getData().getList();
                        listDefoodAdapter.setOnItemClickListener(DeFoodFragment.this);
                        complete.setText(String.valueOf(response.body().getData().getStats().getComplete()));
                        booking.setText(String.valueOf(response.body().getData().getStats().getBooking()));
                        cancel.setText(String.valueOf(response.body().getData().getStats().getCancel()));
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

    private void searchData(){

        edt_cari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listDefoodAdapter.setListDefood(linearSearch(data, s.toString()));
            }
        });

    }

    private ArrayList<DefoodListData> linearSearch(ArrayList<DefoodListData> list, String key){
        ArrayList<DefoodListData> result = new ArrayList<>();
        if(TextUtils.isEmpty(key)){
            return list;
        }
        for(DefoodListData data :list){
            if(String.valueOf(data.getId()).contains(key)){
                result.add(data);
            }
        }
        return result;
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), DetailDefoodActivity.class);
        DefoodListData clickedItem = data.get(position);

        intent.putExtra(EXTRA_ID, clickedItem.getId());

        startActivity(intent);
    }
}
