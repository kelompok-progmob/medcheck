package com.progmob.medcheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.progmob.medcheck.Model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<History> historyArrayList;
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        addData();
        recyclerView = view.findViewById(R.id.fragment_history_recyclerview);
        adapter = new HistoryAdapter(historyArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    void addData() {
        historyArrayList = new ArrayList<>();
        historyArrayList.add(new History("Aditya Herlambang", "Sakit kepala mencengkram? susah mikir? perut mulas-mulas? kaki susah berjalan tapi sempat sampai ke klinik? kok bisa?", "25 November 2019"));
        historyArrayList.add(new History("Kadek Indrayana", "Sakit kepala mencengkram? bibir pecah-pecah? sariawan? ademkan dengan, ADEMSARI", "25 November 2019"));
        historyArrayList.add(new History("Gede Suarnata", "Sakit kepala mencengkram? bibir pecah-pecah? sariawan? ademkan dengan, ADEMSARI", "25 November 2019"));
        historyArrayList.add(new History( "Km. Hendra Triarsa", "Sakit kepala mencengkram? bibir pecah-pecah? sariawan? ademkan dengan, ADEMSARI", "25 November 2019"));
        historyArrayList.add(new History( "Udah Kabur", "Sakit kepala mencengkram? bibir pecah-pecah? sariawan? ademkan dengan, ADEMSARI", "25 November 2019"));

    }
}
