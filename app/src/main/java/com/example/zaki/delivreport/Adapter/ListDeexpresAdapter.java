package com.example.zaki.delivreport.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaki.delivreport.Model.Deexpres;
import com.example.zaki.delivreport.R;

import java.util.ArrayList;

public class ListDeexpresAdapter extends RecyclerView.Adapter<ListDeexpresAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<Deexpres> listDeexpress;

    public ListDeexpresAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Deexpres> getListDeexpress() {
        return listDeexpress;
    }

    public void setListDeexpress(ArrayList<Deexpres> listDeexpress) {
        this.listDeexpress = listDeexpress;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemrow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transaksideexpres, viewGroup, false);
        return new ListDeexpresAdapter.CategoryViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position) {
        holder.id.setText(getListDeexpress().get(position).getId());
        holder.no.setText(getListDeexpress().get(position).getNo());
        holder.customer.setText(getListDeexpress().get(position).getUser());
        holder.driver.setText(getListDeexpress().get(position).getDriver());
        holder.ongkir.setText(getListDeexpress().get(position).getOngkir());
        holder.status.setText(getListDeexpress().get(position).getStatus());
        holder.tanggal.setText(getListDeexpress().get(position).getTanggal());
        holder.btncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.datacustomer.getVisibility() == View.VISIBLE){
                    holder.btncustomer.setBackgroundResource(R.drawable.ic_add_circle);
                    holder.datacustomer.setVisibility(View.GONE);
                } else {
                    holder.btncustomer.setBackgroundResource(R.drawable.ic_remove_circle);
                    holder.datacustomer.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListDeexpress().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView id,no,customer,driver, ongkir,status, tanggal;
        Button btncustomer;
        CardView datacustomer;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_transaksi_deexpress);
            no = itemView.findViewById(R.id.id_notransaksi_deexpres);
            customer = itemView.findViewById(R.id.id_user_deexpres);
            driver = itemView.findViewById(R.id.id_driver_deexpres);
            ongkir = itemView.findViewById(R.id.id_ongkir_deexpres);
            status = itemView.findViewById(R.id.id_status_deexpres);
            tanggal = itemView.findViewById(R.id.id_tanggal_deexpres);
            btncustomer = itemView.findViewById(R.id.btn_customerdeexpres);
            datacustomer = itemView.findViewById(R.id.data_customerdeexpres);
        }
    }
}
