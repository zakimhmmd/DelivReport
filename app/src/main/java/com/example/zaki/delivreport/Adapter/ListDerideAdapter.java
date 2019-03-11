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

import com.example.zaki.delivreport.Model.Deride;
import com.example.zaki.delivreport.Model.DerideListData;
import com.example.zaki.delivreport.R;

import java.util.ArrayList;

public class ListDerideAdapter extends RecyclerView.Adapter<ListDerideAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<DerideListData> listDeride;

    public ListDerideAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<DerideListData> getListDeride() {
        return listDeride;
    }

    public void setListDeride(ArrayList<DerideListData> listDeride) {
        this.listDeride = listDeride;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemrow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transaksideride, viewGroup, false);
        return new CategoryViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position) {
        holder.id.setText(String.valueOf(getListDeride().get(position).getId()));
        holder.customer.setText(getListDeride().get(position).getNamaUser());
        holder.driver.setText(getListDeride().get(position).getNamaDriver());
        holder.ongkir.setText(String.valueOf(getListDeride().get(position).getPrice()));
        holder.status.setText(getListDeride().get(position).getStatus());
        holder.tanggal.setText(getListDeride().get(position).getTanggal());
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
        return getListDeride().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView id,customer,driver, ongkir,status, tanggal;
        Button btncustomer;
        CardView datacustomer;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_transaksi_deride);
            customer = itemView.findViewById(R.id.id_customer_deride);
            driver = itemView.findViewById(R.id.id_driver_deride);
            ongkir = itemView.findViewById(R.id.id_ongkir_deride);
            status = itemView.findViewById(R.id.id_status_deride);
            tanggal = itemView.findViewById(R.id.id_tanggal_deride);
            btncustomer = itemView.findViewById(R.id.btn_customerderide);
            datacustomer = itemView.findViewById(R.id.data_customerderide);
        }
    }
}
