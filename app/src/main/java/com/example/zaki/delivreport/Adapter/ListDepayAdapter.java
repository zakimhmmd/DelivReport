package com.example.zaki.delivreport.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaki.delivreport.Depayment.DePaymentFragment;
import com.example.zaki.delivreport.Model.Depayment.DepayList;
import com.example.zaki.delivreport.Model.Depayment.DepayResponse;
import com.example.zaki.delivreport.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ListDepayAdapter extends RecyclerView.Adapter<ListDepayAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<DepayList> listDepay = new ArrayList<>();

    public ListDepayAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<DepayList> getListDepay() {
        return listDepay;
    }

    public void setListDepay(ArrayList<DepayList> listDepay) {
        this.listDepay.clear();
        this.listDepay.addAll(listDepay);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListDepayAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemrow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transaksidepay, viewGroup, false);
        return new ListDepayAdapter.CategoryViewHolder(itemrow);
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
    public void onBindViewHolder(@NonNull final ListDepayAdapter.CategoryViewHolder holder, int position) {

        holder.id.setText(getListDepay().get(position).getId());
        holder.user.setText(getListDepay().get(position).getNamaMember());
        holder.layanan.setText(getListDepay().get(position).getService());
        holder.status.setText(getListDepay().get(position).getStatus());
        holder.sell.setText(String.valueOf(getListDepay().get(position).getSell()));
        holder.buy.setText(String.valueOf(getListDepay().get(position).getBuy()));
        holder.deskripsi.setText(getListDepay().get(position).getDescription());
        holder.tanggal.setText(formatDate(getListDepay().get(position).getDate()));
        holder.btncustomer.setOnClickListener(v -> {
            if (holder.datacustomer.getVisibility() == View.VISIBLE){
                holder.btncustomer.setBackgroundResource(R.drawable.ic_add_circle);
                holder.datacustomer.setVisibility(View.GONE);
            } else {
                holder.btncustomer.setBackgroundResource(R.drawable.ic_remove_circle);
                holder.datacustomer.setVisibility(View.VISIBLE);
            }
        });
        for (int i = 0; i <getListDepay().size(); i++){
            holder.no.setText(String.valueOf(i));
        }
        String totalSell = String.valueOf(getTotalSell());
        String totalBuy = String.valueOf(getTotalBuy());
        Intent intent = new Intent("totalDepay");
        intent.putExtra("totalSell", totalSell);
        intent.putExtra("totalBuy", totalBuy);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return getListDepay().size();
    }

    public double getTotalSell(){
        double totalSell = 0;
        for (DepayList list : listDepay){
            totalSell += list.getSell();
        }
        return totalSell;
    }

    public double getTotalBuy(){
        double totalBuy = 0;
        for (DepayList list : listDepay){
            totalBuy += list.getBuy();
        }
        return totalBuy;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView id, no, user, layanan, status, buy, sell, deskripsi, tanggal;
        Button btncustomer, btnCari;
        CardView datacustomer;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_transaksi_depay);
            no = itemView.findViewById(R.id.id_notransaksi_depay);
            user = itemView.findViewById(R.id.id_user_depay);
            layanan = itemView.findViewById(R.id.id_layanan_depay);
            status = itemView.findViewById(R.id.id_statusdepay);
            buy = itemView.findViewById(R.id.id_hargabeli);
            sell = itemView.findViewById(R.id.id_hargajual);
            deskripsi = itemView.findViewById(R.id.id_deskripsidepay);
            tanggal = itemView.findViewById(R.id.id_tanggal_depay);
            btncustomer = itemView.findViewById(R.id.btn_customerdepay);
            datacustomer = itemView.findViewById(R.id.data_customerdepay);
            btnCari = itemView.findViewById(R.id.edt_searchkeydepay);

        }
    }
}
