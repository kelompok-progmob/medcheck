package com.progmob.medcheck;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

public class PasienFragment extends Fragment {


    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private PasienAdapter adapter;
    private ArrayList<Pasien> pasienArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasien, container, false);
        addData();
        recyclerView = view.findViewById(R.id.rv_pasien);
        adapter = new PasienAdapter(pasienArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormPasien.class);
                startActivity(intent);
            }
        });

        return view;
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
