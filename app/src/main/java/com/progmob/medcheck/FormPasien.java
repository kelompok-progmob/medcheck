package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;

public class FormPasien extends AppCompatActivity {
    private AppDatabase mDb;
    EditText etNama;
    EditText etJk;
    EditText etLahir;
    Button btnSimpan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_pasien);
        initComponents();

        mDb = AppDatabase.getInstance(getApplicationContext());

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();
            }
        });

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Tambah Pasien");


    }

    private void initComponents(){
        etNama = findViewById(R.id.et_nama);
        etJk = findViewById(R.id.et_jk);
        etLahir = findViewById(R.id.et_lahir);
        btnSimpan = findViewById(R.id.btn_simpan);
    }

    private void onSubmit(){
        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                final Pasien data = new Pasien(
                        etNama.getText().toString(),etJk.getText().toString(),etLahir.getText().toString(),etLahir.getText().toString()
                );

                mDb.pasienDao().insertPasien(data);

                //Intent ke PasienActivity
                Bundle extras = new Bundle();
                extras.putString("from","registation_success");
                Intent intent = new Intent(FormPasien.this, PasienActivity.class);
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
}
