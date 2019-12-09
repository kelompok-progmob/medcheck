package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.progmob.medcheck.Model.Dokter;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.EditPasienBinding;
import com.progmob.medcheck.databinding.FormPasienBinding;

import java.util.List;

import br.com.ilhasoft.support.validation.Validator;

public class EditPasien extends AppCompatActivity implements Validator.ValidationListener {
    EditPasienBinding binding;
    private AppDatabase mDb;
    EditText etNama;
    EditText etJk;
    EditText etLahir;
    Button btnUpdate;
    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.edit_pasien);

        initComponents();
        mDb = AppDatabase.getInstance(getApplicationContext());

        validator =new Validator(binding);
        validator.setValidationListener(this);

        getData();

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Edit Pasien");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();
            }
        });
    }

    private void initComponents(){
        etNama = findViewById(R.id.et_nama);
        etJk = findViewById(R.id.et_jk);
        etLahir = findViewById(R.id.et_lahir);
        btnUpdate = findViewById(R.id.btn_update);
    }

    private void onSubmit(){
        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                final Pasien data = new Pasien(
                        etNama.getText().toString(),etJk.getText().toString(),etLahir.getText().toString(),etLahir.getText().toString()
                );

                mDb.pasienDao().updatePasien(data);

                //Intent ke PasienActivity
                Bundle extras = new Bundle();
                extras.putString("from","update_success");
                Intent intent = new Intent(EditPasien.this, PasienActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getData(){
        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                final Pasien pasien = mDb.pasienDao().loadPasienById(2);
                etNama.setText(pasien.getNamaPasien());
                etJk.setText(pasien.getGender());
                etLahir.setText(pasien.getTglLahir());

            }
        });

    }

    @Override
    public void onValidationSuccess() {

    }

    @Override
    public void onValidationError() {
        Toast.makeText(EditPasien.this, "Mohon Lengkapi Form !", Toast.LENGTH_SHORT).show();
    }
}
