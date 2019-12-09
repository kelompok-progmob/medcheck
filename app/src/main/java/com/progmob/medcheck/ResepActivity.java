package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.progmob.medcheck.databinding.ActivityResepBinding;

public class ResepActivity extends AppCompatActivity {

    ActivityResepBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_resep);

        setComponents();

    }

    private void setComponents(){

    }

}
