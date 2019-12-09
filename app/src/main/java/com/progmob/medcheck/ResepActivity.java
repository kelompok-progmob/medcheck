package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.progmob.medcheck.databinding.ActivityResepBinding;

public class ResepActivity extends AppCompatActivity {

    ActivityResepBinding binding;

    int dokterId, idPasien, suhuBadan, beratBadan, tinggiBadan;
    String keluhan, diagnosa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_resep);

        Bundle extras = getIntent().getExtras();

        dokterId = extras.getInt("dokter_id");
        idPasien = extras.getInt("id_pasien");
        keluhan = extras.getString("keluhan");
        suhuBadan = extras.getInt("suhu_badan");
        beratBadan = extras.getInt("berat_badan");
        tinggiBadan = extras.getInt("tinggi_badan");
        diagnosa = extras.getString("diagnosa_penyakit");

        setComponents();

    }

    private void setComponents(){

    }

}
