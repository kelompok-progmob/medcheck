package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.progmob.medcheck.Model.Obat;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ListObatActivity extends AppCompatActivity {

    AppDatabase mDb;
    private RecyclerView recyclerView;
    private ObatAdapter adapter;
    private List<Obat> obatArrayList;
    FloatingActionButton btnCreate;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_obat);
        initComponents();
        mContext = this;
        mDb = AppDatabase.getInstance(getApplicationContext());

        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                obatArrayList = mDb.obatDao().loadAllObat();
                Log.d("nyobak","isi : "+obatArrayList);
                recyclerView = findViewById(R.id.recyclerViewObat);
                adapter = new ObatAdapter(obatArrayList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
        });

        if(getIntent().getExtras() != null){
            Bundle extras = getIntent().getExtras();
            String from = extras.getString("from");
            if(from.equals("registation_success")){
                new SweetAlertDialog(mContext,SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Success")
                        .setContentText("Berhasil ditambahkan!")
                        .show();
                from = "";
            }
            if(from.equals("update_success")){
                new SweetAlertDialog(mContext,SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Success")
                        .setContentText("Berhasil diupdate!")
                        .show();
                from = "";
            }
        }

    }

    private void initComponents() {

        btnCreate = findViewById(R.id.fab_obat);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, CreateObatActivity.class));
                finish();
            }
        });
    }
}
