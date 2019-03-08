package com.example.zaki.delivreport.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaki.delivreport.DetailActivity;
import com.example.zaki.delivreport.MainActivity;
import com.example.zaki.delivreport.Model.Defood;
import com.example.zaki.delivreport.R;

import java.util.ArrayList;

public class ListDefoodAdapter extends RecyclerView.Adapter<ListDefoodAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<Defood> listDefood;

    public ListDefoodAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Defood> getListDefood() {
        return listDefood;
    }

    public void setListDefood(ArrayList<Defood> listDefood) {
        this.listDefood = listDefood;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transaksidefood, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position) {
        holder.id.setText(getListDefood().get(position).getId());
        holder.no.setText(getListDefood().get(position).getNo());
        holder.customer.setText(getListDefood().get(position).getCustomer());
        holder.driver.setText(getListDefood().get(position).getDriver());
        holder.restoran.setText(getListDefood().get(position).getRestoran());
        holder.harga.setText(getListDefood().get(position).getHarga());
        holder.status.setText(getListDefood().get(position).getStatus());
        holder.ongkir.setText(getListDefood().get(position).getOngkir());
        holder.tanggal.setText(getListDefood().get(position).getTanggal());
        holder.btncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.datacustomer.getVisibility() == View.VISIBLE){
                    holder.btncustomer.setBackgroundResource(R.drawable.ic_add_circle);
                    holder.datacustomer.setVisibility(View.GONE) ;
                } else {
                    holder.btncustomer.setBackgroundResource(R.drawable.ic_remove_circle);
                    holder.datacustomer.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetailActivity.class);
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListDefood().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView id,no,customer,driver, restoran, harga, ongkir,status, tanggal;
        Button btncustomer, btndetail;
        CardView datacustomer;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_transaksi_defood);
            no = itemView.findViewById(R.id.id_notransaksi_defood);
            customer = itemView.findViewById(R.id.id_customer_defood);
            driver = itemView.findViewById(R.id.id_driver_defood);
            restoran = itemView.findViewById(R.id.id_restoran_defood);
            harga = itemView.findViewById(R.id.id_harga_defood);
            ongkir = itemView.findViewById(R.id.id_harga_defood);
            status = itemView.findViewById(R.id.id_status_defood);
            tanggal = itemView.findViewById(R.id.id_tanggal_defood);
            btncustomer = itemView.findViewById(R.id.btn_namacust_defood);
            btndetail = itemView.findViewById(R.id.btn_detail);
            datacustomer = itemView.findViewById(R.id.data_customerdefood);
        }
    }
}
