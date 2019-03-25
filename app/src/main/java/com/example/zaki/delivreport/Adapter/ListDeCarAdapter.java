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

import com.example.zaki.delivreport.Model.DecarListData;
import com.example.zaki.delivreport.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ListDeCarAdapter extends RecyclerView.Adapter<ListDeCarAdapter.CategoryViewHolder>  {

    private Context context;
    private ArrayList<DecarListData> listDecar = new ArrayList<>();

    public ListDeCarAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<DecarListData> getListDecar() {
        return listDecar;
    }

    public void setListDecar(ArrayList<DecarListData> listDecar) {
        this.listDecar.clear();
        this.listDecar.addAll(listDecar);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemrow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transaksidecar, viewGroup, false);
        return new ListDeCarAdapter.CategoryViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position) {
        holder.id.setText(String.valueOf(getListDecar().get(position).getId()));
        holder.customer.setText(getListDecar().get(position).getNamaUser());
        holder.driver.setText(getListDecar().get(position).getNamaDriver());
        holder.ongkir.setText(String.valueOf(getListDecar().get(position).getPrice()));
        holder.status.setText(getListDecar().get(position).getStatus());
        holder.tanggal.setText(formatDate(getListDecar().get(position).getCreatedAt()));
        holder.btncustomer.setOnClickListener(v -> {
            if (holder.datacustomer.getVisibility() == View.VISIBLE){
                holder.btncustomer.setBackgroundResource(R.drawable.ic_add_circle);
                holder.datacustomer.setVisibility(View.GONE);
            } else {
                holder.btncustomer.setBackgroundResource(R.drawable.ic_remove_circle);
                holder.datacustomer.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListDecar().size();
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
    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView id,customer,driver, ongkir,status, tanggal;
        Button btncustomer;
        CardView datacustomer;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_transaksi_decar);
            customer = itemView.findViewById(R.id.id_user_decar);
            driver = itemView.findViewById(R.id.id_driver_decar);
            ongkir = itemView.findViewById(R.id.id_ongkir_decar);
            status = itemView.findViewById(R.id.id_status_decar);
            tanggal = itemView.findViewById(R.id.id_tanggal_decar);
            btncustomer = itemView.findViewById(R.id.btn_customerdecar);
            datacustomer = itemView.findViewById(R.id.data_customerdecar);
        }
    }
}
