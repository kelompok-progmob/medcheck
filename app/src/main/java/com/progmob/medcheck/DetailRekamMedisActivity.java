package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.progmob.medcheck.databinding.ActivityDetailRekamMedisBinding;

public class DetailRekamMedisActivity extends AppCompatActivity {

    ActivityDetailRekamMedisBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(DetailRekamMedisActivity.this, R.layout.activity_detail_rekam_medis);



    }
}
