package com.example.zaki.delivreport.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zaki.delivreport.DetailActivity;
import com.example.zaki.delivreport.Model.Beli;
import com.example.zaki.delivreport.R;

import java.util.ArrayList;

public class ListDataBeliAdapter extends RecyclerView.Adapter<ListDataBeliAdapter.CategoryViewHolder>  {

    private Context context;
    private ArrayList<Beli> listDataBeli;

    public ListDataBeliAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Beli> getListDataBeli() {
        return listDataBeli;
    }

    public void setListDataBeli(ArrayList<Beli> listDataBeli) {
        this.listDataBeli = listDataBeli;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_databeli, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.tvNo.setText(getListDataBeli().get(position).getNo());
        holder.tvQty.setText(getListDataBeli().get(position).getQty());
        holder.tvBarang.setText(getListDataBeli().get(position).getBarang());
        holder.tvCatatan.setText(getListDataBeli().get(position).getCatatan());
        holder.tvHarga.setText(getListDataBeli().get(position).getHarga());
        holder.tvSubtotal.setText(getListDataBeli().get(position).getSubtotal());
    }

    @Override
    public int getItemCount() {
        return getListDataBeli().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvNo, tvQty, tvBarang, tvCatatan, tvHarga, tvSubtotal;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNo = itemView.findViewById(R.id.id_nobeli);
            tvQty = itemView.findViewById(R.id.id_qty);
            tvBarang = itemView.findViewById(R.id.id_barang);
            tvCatatan = itemView.findViewById(R.id.id_catatan);
            tvHarga = itemView.findViewById(R.id.id_harga);
            tvSubtotal = itemView.findViewById(R.id.id_subtotal);
        }
    }
}
