package com.progmob.medcheck;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.progmob.medcheck.Model.Obat;
import com.progmob.medcheck.Model.RekamMedis;
import com.progmob.medcheck.Model.Resep;
import com.progmob.medcheck.Model.ResepWithRelations;
import com.progmob.medcheck.adapter.PilihObatAdapter;
import com.progmob.medcheck.adapter.ResepAdapter;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.ActivityResepBinding;
import com.progmob.medcheck.utils.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ResepActivity extends AppCompatActivity {

    ActivityResepBinding binding;

    int dokterId, idPasien, suhuBadan, beratBadan, tinggiBadan;
    String keluhan, diagnosa, tekananDarah;

    final static int REQUEST_CODE = 321;

    List<ResepWithRelations> resepList = new ArrayList<ResepWithRelations>();

    AppDatabase mDb;
    private RecyclerView recyclerView;
    private ResepAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_resep);

        mDb = AppDatabase.getInstance(getApplicationContext());

        Bundle extras = getIntent().getExtras();

        dokterId = extras.getInt("dokter_id");
        idPasien = extras.getInt("id_pasien");
        keluhan = extras.getString("keluhan");
        suhuBadan = extras.getInt("suhu_badan");
        tekananDarah = extras.getString("tekanan_darah");
        beratBadan = extras.getInt("berat_badan");
        tinggiBadan = extras.getInt("tinggi_badan");
        diagnosa = extras.getString("diagnosa_penyakit");

        setComponents();

    }

    private void setComponents(){
        binding.btnReset.setVisibility(View.GONE);
        binding.btnSimpan.setVisibility(View.GONE);

        binding.btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResepActivity.this,PilihObatActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        adapter = new ResepAdapter(this,resepList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);

        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resepList.clear();
                adapter.notifyDataSetChanged();
                binding.btnReset.setVisibility(View.GONE);
                binding.btnSimpan.setVisibility(View.GONE);
                binding.btnTanpa.setVisibility(View.VISIBLE);
            }
        });

        binding.btnTanpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(ResepActivity.this,SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Apakah Anda Yakin ?")
                        .setContentText("Yakin menyimpan tanpa resep ?")
                        .setConfirmButton("Yakin", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {

                                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(Constants.SP_NAME,0);
                                int dokterId = sharedPref.getInt("dokter_id",0);

                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = new Date();
                                String tanggalRekam = dateFormat.format(date);

                                final RekamMedis rekamMedis = new RekamMedis();
                                rekamMedis.setDokterId(dokterId);
                                rekamMedis.setPasienId(idPasien);
                                rekamMedis.setKeluhan(keluhan);
                                rekamMedis.setTekananDarah(tekananDarah);
                                rekamMedis.setSuhuBadan(suhuBadan);
                                rekamMedis.setBeratBadan(beratBadan);
                                rekamMedis.setTinggiBadan(tinggiBadan);
                                rekamMedis.setDiagnosaPenyakit(diagnosa);
                                rekamMedis.setTanggalRekam(tanggalRekam);


                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDb.rekamMedisDao().insertRekam(rekamMedis);
                                    }
                                });

                                Intent intent = new Intent(ResepActivity.this, MainActivity.class);
                                Bundle extras = new Bundle();
                                extras.putString("resep","success");
                                intent.putExtras(extras);
                                startActivity(intent);
                                finish();

                                sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .setCancelText("Tidak")
                        .show();
            }
        });

        binding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(ResepActivity.this,SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Apakah Anda Yakin ?")
                        .setContentText("Yakin menyimpan rekam medis ini ?")
                        .setConfirmButton("Yakin", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {

                                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(Constants.SP_NAME,0);
                                int dokterId = sharedPref.getInt("dokter_id",0);

                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = new Date();
                                String tanggalRekam = dateFormat.format(date);

                                final RekamMedis rekamMedis = new RekamMedis();
                                rekamMedis.setDokterId(dokterId);
                                rekamMedis.setPasienId(idPasien);
                                rekamMedis.setKeluhan(keluhan);
                                rekamMedis.setTekananDarah(tekananDarah);
                                rekamMedis.setSuhuBadan(suhuBadan);
                                rekamMedis.setBeratBadan(beratBadan);
                                rekamMedis.setTinggiBadan(tinggiBadan);
                                rekamMedis.setDiagnosaPenyakit(diagnosa);
                                rekamMedis.setTanggalRekam(tanggalRekam);

                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDb.rekamMedisDao().insertRekam(rekamMedis);

                                        int lastIdRekamMedis= mDb.rekamMedisDao().lastRekamMedisId();

                                        for(ResepWithRelations data : resepList){
                                            Resep resep = new Resep();
                                            resep.setIdRekamMedis(lastIdRekamMedis);
                                            resep.setIdObat(data.resep.getIdObat());
                                            resep.setJumlah(data.resep.getJumlah());
                                            resep.setKeterangan(data.resep.getKeterangan());

                                            mDb.resepDao().insertResep(resep);
                                            mDb.obatDao().reduceStok(data.resep.getIdObat(),data.resep.getJumlah());
                                        }

                                    }
                                });

                                Intent intent = new Intent(ResepActivity.this, MainActivity.class);
                                Bundle extras = new Bundle();
                                extras.putString("resep","success");
                                intent.putExtras(extras);
                                startActivity(intent);
                                finish();

                                sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .setCancelText("Tidak")
                        .show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && data != null){
            Bundle extras = data.getExtras();
            int idObat = extras.getInt("id_obat");
            String nama = extras.getString("nama");
            int jumlah = extras.getInt("jumlah");
            String keterangan = extras.getString("keterangan");

            Log.d("ResepActivity","Id Obat = "+idObat);

            ResepWithRelations resep = new ResepWithRelations();
            resep.resep = new Resep(0,0,idObat,jumlah,keterangan);
            resep.obats = new ArrayList<Obat>();
            resep.obats.add(new Obat(nama,0));
            resepList.add(resep);
            adapter.notifyDataSetChanged();

            binding.btnReset.setVisibility(View.VISIBLE);
            binding.btnSimpan.setVisibility(View.VISIBLE);
            binding.btnTanpa.setVisibility(View.GONE);

        }

    }
}
