package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.progmob.medcheck.databinding.ActivityDetailRekamMedisBinding;

public class DetailRekamMedisActivity extends AppCompatActivity {

    ActivityDetailRekamMedisBinding binding;

    String namaPasien, keluhan;
    int idPasien;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(DetailRekamMedisActivity.this, R.layout.activity_detail_rekam_medis);

        Bundle extras = getIntent().getExtras();
        namaPasien = extras.getString("nama_pasien");
        keluhan = extras.getString("keluhan");
        idPasien = extras.getInt("id_pasien");

        setComponents();

    }

    private void setComponents(){
        binding.pasien.setText(namaPasien);
        binding.keluhan.setText(keluhan);
    }

}
