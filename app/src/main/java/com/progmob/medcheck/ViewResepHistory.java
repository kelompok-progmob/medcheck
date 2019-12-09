package com.progmob.medcheck;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewResepHistory extends AppCompatActivity {

    Context mContext;

    TextView nama, jmlh, keterangan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resep);
        mContext=this;
    }
}
