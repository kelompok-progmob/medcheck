package com.progmob.medcheck;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.Model.ResepWithRelations;
import com.progmob.medcheck.adapter.ResepAdapter;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.ActivityViewResepBinding;

import java.util.List;

public class ViewResepHistory extends AppCompatActivity {

    Context mContext;

    TextView nama, jmlh, keterangan;

    AppDatabase mDb;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ResepAdapter adapter;
    private List<ResepWithRelations> dataList;

    ActivityViewResepBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_resep);
        mContext=this;
        mDb = AppDatabase.getInstance(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        final int idRekamMedis = extras.getInt("id_rekam_medis");

        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                dataList = mDb.resepDao().getResepByIdRekamMedis(idRekamMedis);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView = findViewById(R.id.recyclerview);
                        adapter = new ResepAdapter(mContext,dataList);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
