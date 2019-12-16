package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

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

import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.ActivityRegisterBinding;
import com.progmob.medcheck.databinding.FormPasienBinding;

import java.util.Calendar;

import br.com.ilhasoft.support.validation.Validator;

public class FormPasien extends AppCompatActivity implements Validator.ValidationListener {

    FormPasienBinding binding;

    private AppDatabase mDb;
    EditText etNama;
    EditText etJk;
    EditText etLahir;
    Button btnSimpan;
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
        binding = DataBindingUtil.setContentView(this, R.layout.form_pasien);

        initComponents();

        mDb = AppDatabase.getInstance(getApplicationContext());

        validator =new Validator(binding);
        validator.setValidationListener(this);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();
            }
        });

        etLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(FormPasien.this,
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
                final AlertDialog.Builder builder = new AlertDialog.Builder(FormPasien.this);
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

    @Override
    public void onValidationSuccess() {

    }

    @Override
    public void onValidationError() {
        Toast.makeText(FormPasien.this, "Mohon Lengkapi Form !", Toast.LENGTH_SHORT).show();
    }
}
