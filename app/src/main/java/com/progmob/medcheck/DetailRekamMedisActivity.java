package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.progmob.medcheck.Model.Dokter;
import com.progmob.medcheck.Model.RekamMedis;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.ActivityDetailRekamMedisBinding;
import com.progmob.medcheck.utils.Constants;

import java.util.List;

import br.com.ilhasoft.support.validation.Validator;

public class DetailRekamMedisActivity extends AppCompatActivity implements Validator.ValidationListener {

    ActivityDetailRekamMedisBinding binding;

    String namaPasien, keluhan;
    int idPasien;
    AppDatabase mDb;
    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(DetailRekamMedisActivity.this, R.layout.activity_detail_rekam_medis);

        mDb = AppDatabase.getInstance(getApplicationContext());
        validator = new Validator(binding);
        validator.setValidationListener(this);

        Bundle extras = getIntent().getExtras();
        namaPasien = extras.getString("nama_pasien");
        keluhan = extras.getString("keluhan");
        idPasien = extras.getInt("id_pasien");

        setComponents();

    }

    private void setComponents(){
        binding.pasien.setText(namaPasien);
        binding.keluhan.setText(keluhan);

        binding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.toValidate();
            }
        });
    }

    @Override
    public void onValidationSuccess() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.SP_NAME, 0);
        final int dokterId = pref.getInt("dokter_id",0);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
//                final RekamMedis data = new RekamMedis(
//                        dokterId,
//                        idPasien,
//                        keluhan,
//                        binding.tekananDarah.getText().toString(),
//                        Integer.parseInt(binding.suhuBadan.getText().toString()),
//                        Integer.parseInt(binding.beratBadan.getText().toString()),
//                        Integer.parseInt(binding.tinggiBadan.getText().toString()),
//                        binding.diagnosa.getText().toString()
//
//                );
//                mDb.rekamMedisDao().insertRekam(data);

//                final int lastRekamMedisId = mDb.rekamMedisDao().lastRekamMedisId();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bundle extras = new Bundle();
                        extras.putInt("dokter_id",dokterId);
                        extras.putInt("id_pasien",idPasien);
                        extras.putString("keluhan",keluhan);
                        extras.putInt("suhu_badan",Integer.parseInt(binding.suhuBadan.getText().toString()));
                        extras.putInt("berat_badan",Integer.parseInt(binding.beratBadan.getText().toString()));
                        extras.putInt("tinggi_badan",Integer.parseInt(binding.tinggiBadan.getText().toString()));
                        extras.putString("diagnosa_penyakit",binding.diagnosa.getText().toString());
                        Intent intent = new Intent(DetailRekamMedisActivity.this, ResepActivity.class);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void onValidationError() {
        Toast.makeText(DetailRekamMedisActivity.this, "Mohon Lengkapi Form !", Toast.LENGTH_SHORT).show();
    }
}
