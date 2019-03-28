package com.example.zaki.delivreport.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zaki.delivreport.Model.Defood.DetailKeranjang;
import com.example.zaki.delivreport.R;

import java.util.ArrayList;

public class ListDetaiDefoodAdapter extends RecyclerView.Adapter<ListDetaiDefoodAdapter.CategoryViewHolder>  {

    private Context context;
    private ArrayList<DetailKeranjang> listData;

    public ListDetaiDefoodAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<DetailKeranjang> getListData() {
        return listData;
    }

    public void setListData(ArrayList<DetailKeranjang> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_databeli, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        int qty = getListData().get(position).getJumlah();
        double harga = getListData().get(position).getHarga();
        double total = qty * harga;
        for (int i = 0; i <= getListData().size(); i++){
            holder.tvNo.setText(String.valueOf(i));
        }
        holder.tvQty.setText(String.valueOf(qty));
        holder.tvBarang.setText(getListData().get(position).getName());
        holder.tvCatatan.setText(getListData().get(position).getCatatan());
        holder.tvHarga.setText(String.valueOf(harga));
        holder.tvSubtotal.setText(String.valueOf(total));
    }

    @Override
    public int getItemCount() {
        return getListData().size();
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
