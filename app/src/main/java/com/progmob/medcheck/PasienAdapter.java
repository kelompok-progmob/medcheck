package com.progmob.medcheck;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.progmob.medcheck.Model.Pasien;

import java.util.ArrayList;

public class PasienAdapter extends RecyclerView.Adapter<PasienAdapter.PasienViewHolder> {

    private ArrayList<Pasien> dataList;

    public PasienAdapter(ArrayList<Pasien> dataList){
        this.dataList = dataList;
    }

    @Override
    public PasienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_pasien, parent, false);
        return new PasienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PasienViewHolder holder, int position) {
        holder.tvNama.setText(dataList.get(position).getNama());
        holder.tvJk.setText(dataList.get(position).getJk());
        holder.tvUmur.setText(dataList.get(position).getUmur());
        holder.tvCreated.setText(dataList.get(position).getCreated_at());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }


    public class PasienViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvJk, tvUmur, tvCreated;

        public PasienViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvJk = itemView.findViewById(R.id.tv_jk);
            tvUmur = itemView.findViewById(R.id.tv_umur);
            tvCreated = itemView.findViewById(R.id.tv_created);
        }
    }
}