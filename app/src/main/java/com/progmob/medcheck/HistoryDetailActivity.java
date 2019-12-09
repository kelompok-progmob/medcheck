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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HistoryDetailActivity extends AppCompatActivity {
    Button btnResep;
    Context mContext;

    TextView pasien, keluhan, diagnosa, tekanan_darah, suhu_badan, berat, tinggi;
    private static final String TAG = "HistoryDetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        Log.d(TAG, "onCreate: started");
        pasien = findViewById(R.id.pasien);
        keluhan = findViewById(R.id.keluhan);
        diagnosa = findViewById(R.id.diagnosa);
        tekanan_darah = findViewById(R.id.tekanan_darah);
        suhu_badan = findViewById(R.id.suhu_badan);
        berat = findViewById(R.id.berat);
        tinggi = findViewById(R.id.tinggi);
        pasien.setText("aa");
        keluhan.setText("aa");
        diagnosa.setText("aaa");
        tekanan_darah.setText("aaa");
        suhu_badan.setText("aaa");
        berat.setText("aaa");
        tinggi.setText("aaa");

        initComponents();
    }
    private void initComponents() {
        btnResep = (Button) findViewById(R.id.btn_view_resep);

        btnResep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ViewResepHistory.class));
            }
        });
    }

}
