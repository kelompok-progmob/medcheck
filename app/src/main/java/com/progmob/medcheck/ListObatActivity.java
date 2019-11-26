package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListObatActivity extends AppCompatActivity {

    FloatingActionButton btnCreate;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_obat);
        mContext = this;
        initComponents();

    }

    private void initComponents() {
        btnCreate = (FloatingActionButton) findViewById(R.id.floatingActionButton2);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, CreateObatActivity.class));
                finish();
            }
        });
    }
}
