package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.progmob.medcheck.Model.Dokter;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.ActivityRegisterBinding;

import java.util.List;

import br.com.ilhasoft.support.validation.Validator;

public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {

    ActivityRegisterBinding binding;
    Validator validator;
    AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        mDb = AppDatabase.getInstance(getApplicationContext());
        validator = new Validator(binding);
        validator.setValidationListener(this);

        setComponents();

    }

    private void setComponents(){
        binding.btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.toValidate();
            }
        });
    }

    @Override
    public void onValidationSuccess() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Dokter> dokter = mDb.dokterDao().loadAllDokter();
                boolean isExists = false;
                for (Dokter data : dokter) {
                    if(data.getUsername().equals(binding.username.getText().toString())){
                        isExists = true;
                    }
                }

                if(!isExists){

                    //Insert Dokter

                    final Dokter data = new Dokter(
                        binding.nama.getText().toString(),
                        binding.spesialis.getText().toString(),
                        binding.username.getText().toString(),
                        binding.password.getText().toString()
                    );
                    mDb.dokterDao().insertDokter(data);

                    //Intent ke Login
                    Bundle extras = new Bundle();
                    extras.putString("from","register_success");
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(RegisterActivity.this, "Username Sudah Terpakai !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onValidationError() {
        Toast.makeText(RegisterActivity.this, "Mohon Lengkapi Form !", Toast.LENGTH_SHORT).show();
    }

}
