package com.progmob.medcheck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;

import java.util.ArrayList;
import java.util.List;

public class PasienAdapter extends RecyclerView.Adapter<PasienAdapter.PasienViewHolder> {

    private List<Pasien> dataList;
    private Context context;
    AppDatabase mDb;

    public PasienAdapter(List<Pasien> dataList){
        this.dataList = dataList;
    }

    @Override
    public PasienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        View view = layoutInflater.inflate(R.layout.item_list_pasien, parent, false);
        return new PasienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PasienViewHolder holder, final int position) {
        mDb = AppDatabase.getInstance(context);
        holder.tvNama.setText(dataList.get(position).getNamaPasien());
        holder.tvJk.setText(dataList.get(position).getGender());
        holder.tvUmur.setText(dataList.get(position).getTglLahir());
        holder.tvCreated.setText(dataList.get(position).getCreatedAt());
        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppExecutors.getInstance().diskIO().execute(new Runnable(){
                    @Override
                    public void run() {
                        mDb.pasienDao().deletePasien(dataList.get(position));
                        dataList.remove(position);
                        ((PasienActivity)context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText( context, "Data Berhasil dihapus!", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            }
                        });
                    }
                });

            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = new Bundle();
                extras.putInt("idPasien",dataList.get(position).getPasienId());
                Intent intent = new Intent(context, EditPasien.class);
                intent.putExtras(extras);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }


    public class PasienViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvJk, tvUmur, tvCreated;
        private ImageView btnEdit, btnHapus;

        public PasienViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvJk = itemView.findViewById(R.id.tv_jk);
            tvUmur = itemView.findViewById(R.id.tv_umur);
            tvCreated = itemView.findViewById(R.id.tv_created);
            btnEdit = itemView.findViewById(R.id.btnUpdatePasien);
            btnHapus = itemView.findViewById(R.id.btnHapusPasien);
        }
    }
}
