package com.progmob.medcheck;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PasienFragment extends Fragment {

    AppDatabase mDb;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private PasienAdapter adapter;
    private List<Pasien> pasienArrayList;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pasien, container, false);
        mDb = AppDatabase.getInstance(getActivity().getApplicationContext());

        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                pasienArrayList = mDb.pasienDao().loadAllPasien();
                Log.d("nyobak","isi : "+pasienArrayList);
                recyclerView = view.findViewById(R.id.rv_pasien);
                adapter = new PasienAdapter(pasienArrayList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
        });


        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormPasien.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity().getIntent().getExtras() != null){
            Bundle extras = getActivity().getIntent().getExtras();
            String from = extras.getString("from");
            if(from.equals("registation_success")){
                new SweetAlertDialog(getActivity(),SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Success")
                        .setContentText("Berhasil ditambahkan!")
                        .show();
                from = "";
            }
            if(from.equals("update_success")){
                new SweetAlertDialog(getActivity(),SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Success")
                        .setContentText("Berhasil diupdate!")
                        .show();
                from = "";
            }
        }
    }

    void addData() {
        pasienArrayList = new ArrayList<>();
        pasienArrayList.add(new Pasien("Aditya Herlambang", "Laki-laki", "50 tahun", "12 Oktober 2019"));
        pasienArrayList.add(new Pasien("I Made Indrayana", "Perempuan", "67 tahun", "12 Oktober 2018"));
        pasienArrayList.add(new Pasien("I Gede Suarnata", "Laki-laki", "40 tahun", "12 Oktober 2019"));
        pasienArrayList.add(new Pasien("I Komang Hendra Triarsa", "Perempuan", "17 tahun", "12 Oktober 2019"));
        pasienArrayList.add(new Pasien("I Wayan Komang", "Laki-laki", "12 tahun", "12 Oktober 2019"));
        pasienArrayList.add(new Pasien("I Ketut Kadek", "Laki-laki", "12 tahun", "12 Oktober 2019"));
    }

}
