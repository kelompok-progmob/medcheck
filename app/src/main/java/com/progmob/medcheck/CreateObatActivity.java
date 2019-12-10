package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.progmob.medcheck.Model.Obat;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.AcitivityCreateObatBinding;

import br.com.ilhasoft.support.validation.Validator;

public class CreateObatActivity extends AppCompatActivity implements Validator.ValidationListener {

    AcitivityCreateObatBinding binding;
    private AppDatabase mDb;
    EditText etNama;
    EditText etJk;
    EditText etLahir;
    Button btnSimpan;
    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.acitivity_create_obat);

        mDb = AppDatabase.getInstance(getApplicationContext());

        validator =new Validator(binding);
        validator.setValidationListener(this);


        binding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();
            }
        });


        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Tambah Obat");
    }

    private void onSubmit(){
        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                final Obat data = new Obat(
                       binding.tvNamaobat.getText().toString(), Integer.parseInt(binding.tvStok.getText().toString())
                );

                mDb.obatDao().insertObat(data);

                //Intent ke PasienActivity
                Bundle extras = new Bundle();
                extras.putString("from","registation_success");
                Intent intent = new Intent(CreateObatActivity.this, ListObatActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onValidationSuccess() {

    }

    @Override
    public void onValidationError() {
        Toast.makeText(CreateObatActivity.this, "Mohon Lengkapi Form !", Toast.LENGTH_SHORT).show();
    }
}
