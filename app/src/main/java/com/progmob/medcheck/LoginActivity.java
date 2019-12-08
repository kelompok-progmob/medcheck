package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.progmob.medcheck.Model.Dokter;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.ActivityLoginBinding;
import com.progmob.medcheck.utils.Constants;

import java.util.List;

import br.com.ilhasoft.support.validation.Validator;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        mDb = AppDatabase.getInstance(getApplicationContext());
        setComponents();

        if(getIntent().getExtras() != null){
            Bundle extras = getIntent().getExtras();
            String from = extras.getString("from");
            if(from.equals("register_success")){
                new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Success")
                        .setContentText("Register Berhasil !")
                        .show();
            }
        }

    }

    private void setComponents(){
        binding.btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        final List<Dokter> dokter = mDb.dokterDao().loadAllDokter();
                        boolean isExists = false;
                        for (Dokter data : dokter) {
                            if(data.getUsername().equals(binding.username.getText().toString()) && data.getPassword().equals(binding.password.getText().toString())){
                                SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.SP_NAME, 0);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putInt("dokter_id", data.getDokterId());
                                editor.putString("nama_dokter", data.getNamaDokter());
                                editor.putString("spesialisasi", data.getSpesialisasi());
                                editor.commit();

                                isExists = true;
                            }
                        }

                        if(isExists){
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    new SweetAlertDialog(LoginActivity.this,SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Failed")
                                            .setContentText("Username atau Password Salah !")
                                            .show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

}
