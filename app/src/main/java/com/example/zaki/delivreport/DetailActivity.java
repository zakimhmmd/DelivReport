package com.example.zaki.delivreport;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zaki.delivreport.Adapter.ListDataBeliAdapter;
import com.example.zaki.delivreport.Model.Beli;
import com.example.zaki.delivreport.Model.DataBeli;

import java.util.ArrayList;

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
