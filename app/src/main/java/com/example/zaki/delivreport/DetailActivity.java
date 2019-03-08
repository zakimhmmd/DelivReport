package com.example.zaki.delivreport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zaki.delivreport.Adapter.ListDataBeliAdapter;
import com.example.zaki.delivreport.Model.Beli;
import com.example.zaki.delivreport.Model.DataBeli;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ArrayList<Beli> list = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = findViewById(R.id.rv_databeli);
        recyclerView.setHasFixedSize(true);
        list.addAll(DataBeli.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListDataBeliAdapter listDataBeliAdapter = new ListDataBeliAdapter(this);
        listDataBeliAdapter.setListDataBeli(list);
        recyclerView.setAdapter(listDataBeliAdapter);
    }
}
