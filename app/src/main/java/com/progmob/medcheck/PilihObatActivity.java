package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.progmob.medcheck.Model.Obat;
import com.progmob.medcheck.adapter.PilihObatAdapter;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.ActivityPilihObatBinding;

import java.util.List;

public class PilihObatActivity extends AppCompatActivity {

    ActivityPilihObatBinding binding;
    AppDatabase mDb;
    private RecyclerView recyclerView;
    private PilihObatAdapter adapter;
    private List<Obat> obatArrayList;
    FloatingActionButton btnCreate;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_pilih_obat);

        mContext = this;
        mDb = AppDatabase.getInstance(getApplicationContext());

        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                obatArrayList = mDb.obatDao().loadAllObat();
                recyclerView = findViewById(R.id.recyclerViewObat);
                adapter = new PilihObatAdapter(mContext,obatArrayList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void giveResult(int id_obat, String nama, int jumlah, String keterangan){
        Intent returnIntent = new Intent();
        Bundle extras = new Bundle();
        extras.putInt("id_obat",id_obat);
        extras.putInt("jumlah",jumlah);
        extras.putString("nama",nama);
        extras.putString("keterangan",keterangan);
        returnIntent.putExtras(extras);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
