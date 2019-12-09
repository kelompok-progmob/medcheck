package com.progmob.medcheck;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.progmob.medcheck.Model.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

private ArrayList<History> dataListHistory;
private Context context;

    public HistoryAdapter(Context context, ArrayList<History> dataListHistory){
            this.dataListHistory = dataListHistory;
            this.context = context;
    }

    public HistoryAdapter(ArrayList<History> historyArrayList) {this.dataListHistory = historyArrayList;}

    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent, false);
            return new HistoryViewHolder(view);
            }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
            final History history = dataListHistory.get(position);
            String nama = history.getNama();
            String keluhan = history.getKeluhan();

            holder.tvNama.setText(nama);
            holder.tvKeluhan.setText(keluhan);
            holder.detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, HistoryDetailActivity.class);
                    context.startActivity(intent);
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
