package com.example.zaki.delivreport.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaki.delivreport.Defood.DetailDefoodActivity;
import com.example.zaki.delivreport.Model.Defood.DefoodListData;
import com.example.zaki.delivreport.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ListDefoodAdapter extends RecyclerView.Adapter<ListDefoodAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<DefoodListData> listDefood = new ArrayList<>();
    private OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        clickListener = listener;
    }
    public ListDefoodAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<DefoodListData> getListDefood() {
        return listDefood;
    }

    public void setListDefood(ArrayList<DefoodListData> listDefood) {
        this.listDefood.clear();
        this.listDefood.addAll(listDefood);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transaksidefood, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position) {

        holder.id.setText(String.valueOf(getListDefood().get(position).getId()));
        holder.customer.setText(getListDefood().get(position).getNamaUser());
        holder.driver.setText(getListDefood().get(position).getNamaDriver());
        holder.restoran.setText(getListDefood().get(position).getNamaRestoran());
        holder.harga.setText(String.valueOf(getListDefood().get(position).getFoods()));
        holder.status.setText(getListDefood().get(position).getStatus());
        holder.ongkir.setText(String.valueOf(getListDefood().get(position).getDelivery()));
        holder.tanggal.setText(formatDate(getListDefood().get(position).getUpdatedAt()));
        holder.btncustomer.setOnClickListener(v -> {
            if (holder.datacustomer.getVisibility() == View.VISIBLE){
                holder.btncustomer.setBackgroundResource(R.drawable.ic_add_circle);
                holder.datacustomer.setVisibility(View.GONE) ;
            } else {
                holder.btncustomer.setBackgroundResource(R.drawable.ic_remove_circle);
                holder.datacustomer.setVisibility(View.VISIBLE);
            }
        });
    }

    private static String formatDate(String timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdfResult = new SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault());

        Date date = new Date();
        try {
            date = sdf.parse(timestamp);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sdfResult.format(date);
    }

    @Override
    public int getItemCount() {
        return listDefood.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView id,customer,driver, restoran, harga, ongkir,status, tanggal;
        Button btncustomer, btndetail;
        CardView datacustomer;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_transaksi_defood);
            customer = itemView.findViewById(R.id.id_customer_defood);
            driver = itemView.findViewById(R.id.id_driver_defood);
            restoran = itemView.findViewById(R.id.id_restoran_defood);
            harga = itemView.findViewById(R.id.id_harga_defood);
            ongkir = itemView.findViewById(R.id.id_ongkir_defood);
            status = itemView.findViewById(R.id.id_status_defood);
            tanggal = itemView.findViewById(R.id.id_tanggal_defood);
            btncustomer = itemView.findViewById(R.id.btn_namacust_defood);
            btndetail = itemView.findViewById(R.id.btn_detail);
            datacustomer = itemView.findViewById(R.id.data_customerdefood);

            btndetail.setOnClickListener(v -> {
                if (clickListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        clickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
