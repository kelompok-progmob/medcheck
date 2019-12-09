package com.progmob.medcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.abdeveloper.library.MultiSelectDialog;
import com.abdeveloper.library.MultiSelectModel;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.database.AppDatabase;
import com.progmob.medcheck.database.AppExecutors;
import com.progmob.medcheck.databinding.ActivityRekamMedisBinding;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RekamMedisActivity extends AppCompatActivity {

    ActivityRekamMedisBinding binding;
    MultiSelectDialog multiSelectDialog;
    final String TAG = "RekamMedis";
    AppDatabase mDb;

    String pasienId, pasienNama;
    SweetAlertDialog loadingDialog;

    ArrayList<MultiSelectModel> listPasien = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rekam_medis);

        mDb = AppDatabase.getInstance(getApplicationContext());


        loadingDialog = new SweetAlertDialog(RekamMedisActivity.this,SweetAlertDialog.PROGRESS_TYPE);
        loadingDialog.show();

        retrieveData();

    }

    private void retrieveData(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Pasien> pasien = mDb.pasienDao().loadAllPasien();
                for(Pasien data : pasien){
                    listPasien.add(new MultiSelectModel(data.pasienId,data.namaPasien));
                }

                if(loadingDialog.isShowing()){
                    loadingDialog.dismissWithAnimation();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setComponents();
                    }
                });
            }
        });
    }

    private void setComponents(){
        binding.pasienContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickPasien();
            }
        });

        binding.pasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickPasien();
            }
        });

        binding.pasien.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    pickPasien();
                }
            }
        });
    }

    private void pickPasien(){
        multiSelectDialog = new MultiSelectDialog()
                .title("Pilih Pasien")
                .titleSize(25)
                .positiveText("Selesai")
                .negativeText("Batal")
                .setMinSelectionLimit(1)
                .setMaxSelectionLimit(1)
                .multiSelectList(listPasien)
                .onSubmit(new MultiSelectDialog.SubmitCallbackListener() {
                    @Override
                    public void onSelected(ArrayList<Integer> selectedIds, ArrayList<String> selectedNames, String dataString) {
                        //will return list of selected IDS
                        for (int i = 0; i < selectedIds.size(); i++) {
                            Log.d(TAG,"id = "+selectedIds.get(i)+" name = "+selectedNames.get(i)+" dataString = "+dataString);
                            binding.pasien.setText(selectedNames.get(i));
                            pasienId =  String.valueOf(selectedIds.get(i));
                            pasienNama =  String.valueOf(selectedNames.get(i));

                            hideSoftKeyboard();
                        }


                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG,"Dialog cancelled");
                    }
                });
        multiSelectDialog.show(getSupportFragmentManager(), "multiSelectDialog");
    }

    private void hideSoftKeyboard(){
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

}
