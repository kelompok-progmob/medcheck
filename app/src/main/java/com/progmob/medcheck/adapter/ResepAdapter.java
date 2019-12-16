package com.progmob.medcheck.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.progmob.medcheck.Model.Resep;
import com.progmob.medcheck.Model.ResepWithRelations;
import com.progmob.medcheck.R;
import com.progmob.medcheck.database.AppDatabase;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;

public class ResepAdapter extends RecyclerView.Adapter<ResepAdapter.MyViewHolder> {
    private Context context;
    private List<ResepWithRelations> mResepList;

    public ResepAdapter(Context context, List<ResepWithRelations> mResepList) {
        this.context = context;
        this.mResepList = mResepList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_obat_resep, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResepAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.mNama.setText(mResepList.get(i).obats.get(0).getNamaObat());
        myViewHolder.mJumlah.setText(String.valueOf(mResepList.get(i).resep.getJumlah()));
        myViewHolder.mKeterangan.setText(mResepList.get(i).resep.getKeterangan());
    }

    @Override
    public int getItemCount() {
        if (mResepList == null) {
            return 0;
        }
        return mResepList.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mNama, mJumlah, mKeterangan;
        AppDatabase mDb;
        CardView mCard;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);

            mNama = itemView.findViewById(R.id.item_obat_nama);
            mJumlah = itemView.findViewById(R.id.item_obat_jmlh);
            mKeterangan = itemView.findViewById(R.id.item_obat_keterangan);

        }
    }
}
