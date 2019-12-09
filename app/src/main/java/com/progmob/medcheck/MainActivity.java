package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ImageFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.progmob.medcheck.databinding.ActivityMainBinding;
import com.progmob.medcheck.utils.Constants;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setComponents();

    }

    private void setComponents(){
        ImageView ivPasien = findViewById(R.id.iv_pasien);
        ImageView ivListobat = findViewById(R.id.iv_listobat);
        CardView rekamMedis = findViewById(R.id.rekam_medis);

        ivPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PasienActivity.class);
                startActivity(intent);
            }
        });

        ivListobat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListObatActivity.class);
                startActivity(intent);
            }
        });

        rekamMedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RekamMedisActivity.class);
                startActivity(intent);
            }
        });

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.SP_NAME, 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
