package com.progmob.medcheck.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.progmob.medcheck.Model.Obat;
import com.progmob.medcheck.PilihObatActivity;
import com.progmob.medcheck.R;
import com.progmob.medcheck.database.AppDatabase;

import net.steamcrafted.materialiconlib.MaterialIconView;

import java.util.List;

public class PilihObatAdapter extends RecyclerView.Adapter<PilihObatAdapter.MyViewHolder> {
    private Context context;
    private List<Obat> mDataList;
    private LayoutInflater inflater;

    public PilihObatAdapter(Context context, List<Obat> mDataList) {
        this.context = context;
        this.mDataList = mDataList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.from(context).inflate(R.layout.item_obat_pilih, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PilihObatAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.mNama.setText(mDataList.get(i).namaObat);
        myViewHolder.mStok.setText(String.valueOf(mDataList.get(i).stok));
        myViewHolder.mBtnTambah.setTag(i);
        myViewHolder.mBtnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position = (Integer) view.getTag();
                Obat obat = mDataList.get(position);

                final int idObat = obat.getObatId();
                final String nama = obat.getNamaObat();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View dialogView = inflater.inflate(R.layout.dialog_tambah_obat_rekam, null);

                builder.setView(dialogView)

                        .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                EditText mJumlah = (EditText) dialogView.findViewById(R.id.tambah_obat_rekam_jumlah);
                                int jumlah = Integer.valueOf(mJumlah.getText().toString());
                                EditText mKeterangan = (EditText) dialogView.findViewById(R.id.tambah_obat_rekam_keterangan);
                                String keterangan = mKeterangan.getText().toString();
                                ((PilihObatActivity)context).giveResult(idObat, nama, jumlah, keterangan);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mNama, mStok;
        MaterialIconView mBtnTambah;
        AppDatabase mDb;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);

            mNama = itemView.findViewById(R.id.nama_obat);
            mStok = itemView.findViewById(R.id.stok_obat);
            mBtnTambah = itemView.findViewById(R.id.btn_tambah_obat);

        }
    }
}
