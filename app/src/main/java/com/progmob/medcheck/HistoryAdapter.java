package com.progmob.medcheck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.progmob.medcheck.Model.History;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.Model.RekamMedis;
import com.progmob.medcheck.Model.RekamMedisWithRelation;
import com.progmob.medcheck.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    String namaPasien;
    int idPasien;
    private List<RekamMedisWithRelation> dataListHistory;
    private Context context;
    AppDatabase mDb;


    public HistoryAdapter(List<RekamMedisWithRelation> dataList){this.dataListHistory = dataList; }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        View view = layoutInflater.inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, final int position) {
        mDb = AppDatabase.getInstance(context);
//            final History history = dataListHistory.get(position);
//            String nama = history.getNama();
//            String keluhan = history.getKeluhan();
//        holder.tvNama.setText(dataListHistory.get(position).rekamMedis);
        holder.tvKeluhan.setText(dataListHistory.get(position).rekamMedis.getKeluhan());
        holder.tvCreated.setText(dataListHistory.get(position).rekamMedis.getTanggalRekam());
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = new Bundle();
                extras.putInt("id_rekam",dataListHistory.get(position).rekamMedis.getRekamId());
                Intent intent = new Intent(context, HistoryDetailActivity.class);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
            }

    @Override
    public int getItemCount() {
            return (dataListHistory != null) ? dataListHistory.size() : 0;
            }

    public void clear(){
        int size = this.dataListHistory.size();
        this.dataListHistory.clear();
        notifyItemRangeRemoved(0,size);
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvKeluhan, tvCreated;
        Button detail;
        public HistoryViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_history_nama);
            tvKeluhan = itemView.findViewById(R.id.tv_history_keluhan);
            tvCreated = itemView.findViewById(R.id.tv_history_created);
            detail = itemView.findViewById(R.id.history_detail_button);
        }
    }

}
