package com.progmob.medcheck;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.progmob.medcheck.Model.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

private ArrayList<History> dataListHistory;

    public HistoryAdapter(ArrayList<History> dataListHistory){
            this.dataListHistory = dataListHistory;
            }

    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.item_history, parent, false);
            return new HistoryViewHolder(view);
            }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
            holder.tvNama.setText(dataListHistory.get(position).getHistoryNama());
            holder.tvKeluhan.setText(dataListHistory.get(position).getHistoryKeluhan());
            holder.tvCreated.setText(dataListHistory.get(position).getHistoryCreated_at());
            }

    @Override
    public int getItemCount() {
            return (dataListHistory != null) ? dataListHistory.size() : 0;
            }


    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvKeluhan, tvCreated;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_history_nama);
            tvKeluhan = itemView.findViewById(R.id.tv_history_keluhan);
            tvCreated = itemView.findViewById(R.id.tv_history_created);
        }
    }
}
