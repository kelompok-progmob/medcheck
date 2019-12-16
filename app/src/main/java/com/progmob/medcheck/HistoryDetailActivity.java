package com.progmob.medcheck;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.progmob.medcheck.Model.RekamMedisWithRelation;
import com.progmob.medcheck.Model.Resep;
import com.progmob.medcheck.Model.ResepWithRelations;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.ActivityDetailHistoryBinding;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.List;

public class HistoryDetailActivity extends AppCompatActivity {

    ActivityDetailHistoryBinding binding;
    private AppDatabase mDb;
    Button btnResep;
    Context mContext;

    TextView pasien, keluhan, diagnosa, tekanan_darah, suhu_badan, berat, tinggi;
    private static final String TAG = "HistoryDetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(HistoryDetailActivity.this, R.layout.activity_detail_history);
        mDb = AppDatabase.getInstance(getApplicationContext());
        initComponents();
        Log.d(TAG, "onCreate: started");
        Bundle extras = getIntent().getExtras();
        final int idRekam = extras.getInt("id_rekam_medis", 1);
        getData(idRekam);

    }
    private void initComponents() {
        keluhan = findViewById(R.id.keluhan);
        diagnosa = findViewById(R.id.diagnosa);
        tekanan_darah = findViewById(R.id.tekanan_darah);
        suhu_badan = findViewById(R.id.suhu_badan);
        berat = findViewById(R.id.berat);
        tinggi = findViewById(R.id.tinggi);
        pasien = findViewById(R.id.pasien);

        btnResep = (Button) findViewById(R.id.btn_view_resep);

    }

    private void getData(final int idRekam){
        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                final RekamMedisWithRelation rekamMedisWithRelation = mDb.rekamMedisDao().loadRekamMedisById(idRekam);
                final List<ResepWithRelations> resepList = mDb.resepDao().getResepByIdRekamMedis(idRekam);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        keluhan.setText(rekamMedisWithRelation.rekamMedis.getKeluhan());
                        diagnosa.setText(rekamMedisWithRelation.rekamMedis.getDiagnosaPenyakit());
                        tekanan_darah.setText(rekamMedisWithRelation.rekamMedis.getTekananDarah());
                        suhu_badan.setText(String.valueOf(rekamMedisWithRelation.rekamMedis.getSuhuBadan()));
                        berat.setText(String.valueOf(rekamMedisWithRelation.rekamMedis.getBeratBadan()));
                        tinggi.setText(String.valueOf(rekamMedisWithRelation.rekamMedis.getTinggiBadan()));
                        pasien.setText(rekamMedisWithRelation.pasiens.get(0).getNamaPasien());

                        if(resepList.size() < 1){
                            binding.statusResep.setVisibility(View.VISIBLE);
                            binding.btnViewResep.setVisibility(View.GONE);
                        }
                        else{
                            binding.statusResep.setVisibility(View.GONE);
                            binding.btnViewResep.setVisibility(View.VISIBLE);
                        }

                        btnResep.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(HistoryDetailActivity.this, ViewResepHistory.class);
                                Bundle extras = new Bundle();
                                extras.putInt("id_rekam_medis",rekamMedisWithRelation.rekamMedis.getRekamId());
                                intent.putExtras(extras);
                                startActivity(intent);
                            }
                        });

                    }
                });
            }
        });

    }

}
