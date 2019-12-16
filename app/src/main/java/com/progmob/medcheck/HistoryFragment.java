package com.progmob.medcheck;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.progmob.medcheck.Model.RekamMedis;
import com.progmob.medcheck.Model.RekamMedisWithRelation;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    AppDatabase mDb;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<RekamMedisWithRelation> historyArrayList;
    Button button;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        mDb = AppDatabase.getInstance(getActivity().getApplicationContext());


        AppExecutors.getInstance().diskIO().execute(new Runnable(){
            @Override
            public void run() {
                historyArrayList = mDb.rekamMedisDao().loadAllRekam();
                Log.d("nyobak","isi : "+historyArrayList.get(0).rekamMedis.getKeluhan());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView = view.findViewById(R.id.fragment_history_recyclerview);
                        adapter = new HistoryAdapter(historyArrayList);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });


        return view;
    }

}