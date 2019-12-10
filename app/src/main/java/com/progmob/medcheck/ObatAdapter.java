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

import com.progmob.medcheck.Model.Obat;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;

import java.util.List;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.ObatViewHolder> {

    private List<Obat> dataList;
    private Context context;
    AppDatabase mDb;

    public ObatAdapter(List<Obat> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ObatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        View view = layoutInflater.inflate(R.layout.item_obat, parent, false);
        return new ObatAdapter.ObatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ObatViewHolder holder, final int position) {
        mDb = AppDatabase.getInstance(context);
        holder.tvNama.setText(dataList.get(position).getNamaObat());
        holder.tvStok.setText(String.valueOf(dataList.get(position).getStok()));
        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppExecutors.getInstance().diskIO().execute(new Runnable(){
                    @Override
                    public void run() {
                        mDb.obatDao().deleteObat(dataList.get(position));
                        dataList.remove(position);
                        ((ListObatActivity)context).runOnUiThread(new Runnable() {
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
                Log.d("IDOBAT","ini id obat : "+dataList.get(position).getObatId());
                extras.putInt("id",dataList.get(position).getObatId());
                Intent intent = new Intent(context, EditObat.class);
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

    public class ObatViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvStok;
        private ImageView btnEdit, btnHapus;

        public ObatViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.nama_obat);
            tvStok = itemView.findViewById(R.id.stok_obat);
            btnEdit = itemView.findViewById(R.id.btnEditObat);
            btnHapus = itemView.findViewById(R.id.btnHapusObat);
        }
    }
}
