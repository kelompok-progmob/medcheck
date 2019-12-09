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

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private ArrayList<History> historyArrayList;
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        addData();
        RecyclerView recyclerView= (RecyclerView) inflater.inflate(R.layout.fragment_history, container, false);
        ContentAdapter adapter = new ContentAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
        private static final int LENGTH = 50;

        public ContentAdapter() {
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.fragment_history, parent, false));
            }

            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), DetailHistory.class));

            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            ViewHolder viewHolder = new ViewHolder (LayoutInflater.from(parent.getContext()), parent);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }



    void addData() {
        historyArrayList = new ArrayList<>();
        historyArrayList.add(new History("Aditya Herlambang", "Sakit kepala mencengkram? susah mikir? perut mulas-mulas? kaki susah berjalan tapi sempat sampai ke klinik? kok bisa?", "25 November 2019"));
        historyArrayList.add(new History("Kadek Indrayana", "Sakit kepala mencengkram? bibir pecah-pecah? sariawan? ademkan dengan, ADEMSARI", "25 November 2019"));
        historyArrayList.add(new History("Gede Suarnata", "Sakit kepala mencengkram? bibir pecah-pecah? sariawan? ademkan dengan, ADEMSARI", "25 November 2019"));
        historyArrayList.add(new History("Km. Hendra Triarsa", "Sakit kepala mencengkram? bibir pecah-pecah? sariawan? ademkan dengan, ADEMSARI", "25 November 2019"));
        historyArrayList.add(new History("Udah Kabur", "Sakit kepala mencengkram? bibir pecah-pecah? sariawan? ademkan dengan, ADEMSARI", "25 November 2019"));

    }
}
