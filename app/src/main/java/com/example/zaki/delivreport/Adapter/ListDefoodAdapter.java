package com.example.zaki.delivreport.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.zaki.delivreport.DetailActivity;
import com.example.zaki.delivreport.Model.DefoodListData;
import com.example.zaki.delivreport.Model.DefoodStats;
import com.example.zaki.delivreport.R;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListDefoodAdapter extends RecyclerView.Adapter<ListDefoodAdapter.CategoryViewHolder> implements Filterable {

    private Context context;
    private ArrayList<DefoodListData> listDefood = new ArrayList<>();
    private ArrayList<DefoodListData> listFilter;

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
        holder.tanggal.setText(getListDefood().get(position).getUpdatedAt());
        holder.btncustomer.setOnClickListener(v -> {
            if (holder.datacustomer.getVisibility() == View.VISIBLE){
                holder.btncustomer.setBackgroundResource(R.drawable.ic_add_circle);
                holder.datacustomer.setVisibility(View.GONE) ;
            } else {
                holder.btncustomer.setBackgroundResource(R.drawable.ic_remove_circle);
                holder.datacustomer.setVisibility(View.VISIBLE);
            }
        });

        holder.btndetail.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), DetailActivity.class);
            holder.itemView.getContext().startActivity(i);
        });
    }


    public void filter(String keySearch){
        if (keySearch.isEmpty()){
            listDefood.addAll(listFilter);
        } else {
            for (DefoodListData row : listFilter){
                String id = String.valueOf(row.getId());
                if (id.toLowerCase().contains(keySearch.toLowerCase()) ||
                        row.getNamaUser().toLowerCase().contains(keySearch.toLowerCase()) ||
                        row.getNamaDriver().toLowerCase().contains(keySearch.toLowerCase())){
                    listDefood.add(row);
                }
            }
        }
        notifyDataSetChanged();
    }



    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()){
                     listDefood = listFilter;
                } else {
                    ArrayList<DefoodListData> filteredList = new ArrayList<>();
                    for (DefoodListData row : listFilter){
                        String id = String.valueOf(row.getId());
                        if (id.toLowerCase().contains(charString.toLowerCase()) ||
                                row.getNamaUser().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getNamaDriver().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                    listDefood = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listDefood;

                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listDefood = (ArrayList<DefoodListData>) results.values;
                notifyDataSetChanged();
            }
        };
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
        }
    }
}
