package com.example.zaki.delivreport.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaki.delivreport.Model.Depay;
import com.example.zaki.delivreport.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ListDepayAdapter extends RecyclerView.Adapter<ListDepayAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<Depay> listDepay;

    public ListDepayAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Depay> getListDepay() {
        return listDepay;
    }

    public void setListDepay(ArrayList<Depay> listDepay) {
        this.listDepay = listDepay;
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
        holder.no.setText(getListDepay().get(position).getNo());
        holder.user.setText(getListDepay().get(position).getUser());
        holder.layanan.setText(getListDepay().get(position).getLayanan());
        holder.status.setText(getListDepay().get(position).getStatus());
        holder.sell.setText(getListDepay().get(position).getSell());
        holder.buy.setText(getListDepay().get(position).getBuy());
        holder.deskripsi.setText(getListDepay().get(position).getDeskripsi());
        holder.tanggal.setText(formatDate(getListDepay().get(position).getTanggal()));
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
        return getListDepay().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView id, no, user, layanan, status, buy, sell, deskripsi, tanggal;
        Button btncustomer;
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
        }
    }
}
