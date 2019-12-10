package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.progmob.medcheck.Model.Obat;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.AcitivityCreateObatBinding;
import com.progmob.medcheck.databinding.EditObatBinding;

import br.com.ilhasoft.support.validation.Validator;

public class EditObat extends AppCompatActivity implements Validator.ValidationListener {

    EditObatBinding binding;
    private AppDatabase mDb;
    Validator validator;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.edit_obat);

        mDb = AppDatabase.getInstance(getApplicationContext());

        validator = new Validator(binding);
        validator.setValidationListener(this);

        Bundle extras = getIntent().getExtras();
        final int idObat = extras.getInt("id",0);
        getData(idObat);

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit(idObat);
            }
        });

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Edit Obat");

    }

    private void onSubmit(final int idObat){
        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                final Obat data = new Obat(
                        idObat, binding.tvNamaobat.getText().toString(), Integer.parseInt(binding.tvStok.getText().toString())
                );
                Log.d("IDOBAT","ini stok obat  : "+Integer.parseInt(binding.tvStok.getText().toString()));
                Log.d("IDOBAT","ini nama obat baru : "+binding.tvNamaobat.getText().toString());

                mDb.obatDao().updateObat(data);

                Bundle extras = new Bundle();
                extras.putString("from","update_success");
                Intent intent = new Intent(EditObat.this, ListObatActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getData(final int idObat){
        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                Log.d("IDOBAT","ini id obat : "+idObat);
                final Obat obat = mDb.obatDao().loadObatById(idObat);
                Log.d("IDOBAT","ini nama obat : "+obat.getNamaObat());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.tvNamaobat.setText(obat.getNamaObat());
                        binding.tvStok.setText(String.valueOf(obat.getStok()));
                    }
                });
            }
        });

    }

    @Override
    public void onValidationSuccess() {

    }

    @Override
    public void onValidationError() {
        Toast.makeText(EditObat.this, "Mohon Lengkapi Form !", Toast.LENGTH_SHORT).show();
    }
}
