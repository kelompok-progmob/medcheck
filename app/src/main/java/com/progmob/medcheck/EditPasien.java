package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.progmob.medcheck.Model.Dokter;
import com.progmob.medcheck.Model.Obat;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.EditPasienBinding;
import com.progmob.medcheck.databinding.FormPasienBinding;

import java.util.Calendar;
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
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    String jk;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.edit_pasien);

        initComponents();
        mDb = AppDatabase.getInstance(getApplicationContext());

        validator =new Validator(binding);
        validator.setValidationListener(this);

        Bundle extras = getIntent().getExtras();
        final int idPasien = extras.getInt("idPasien",0);
        getData(idPasien);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Edit Pasien");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit(idPasien);
            }
        });

        etLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(EditPasien.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                etLahir.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        etJk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setup the alert builder
                final AlertDialog.Builder builder = new AlertDialog.Builder(EditPasien.this);
                builder.setTitle("Pilih Jenis Kelamin");
                // add a radio button list
                final String[] jks = {"Laki-laki", "Perempuan"};
                int checkedItem = 1; // cow
                builder.setSingleChoiceItems(jks, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jk = jks[which];
                        Log.d("jeniskelamin",""+jk);
                    }
                });
                // add OK and Cancel buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etJk.setText(jk);
                    }
                });
                builder.setNegativeButton("Cancel", null);
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    private void initComponents(){
        etNama = findViewById(R.id.et_nama);
        etJk = findViewById(R.id.et_jk);
        etLahir = findViewById(R.id.et_lahir);
        btnUpdate = findViewById(R.id.btn_update);
    }

    private void onSubmit(final int idPasien){
        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                final Pasien data = new Pasien(
                        idPasien, etNama.getText().toString(),etJk.getText().toString(),etLahir.getText().toString(),etLahir.getText().toString()
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

    private void getData(final int idPasien){
        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                final Pasien pasien = mDb.pasienDao().loadPasienById(idPasien);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etNama.setText(pasien.getNamaPasien());
                        etJk.setText(pasien.getGender());
                        etLahir.setText(pasien.getTglLahir());
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
        Toast.makeText(EditPasien.this, "Mohon Lengkapi Form !", Toast.LENGTH_SHORT).show();
    }
}
