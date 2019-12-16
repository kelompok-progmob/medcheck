package com.progmob.medcheck.database;


import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.progmob.medcheck.Dao.DokterDao;
import com.progmob.medcheck.Dao.ObatDao;
import com.progmob.medcheck.Dao.PasienDao;
import com.progmob.medcheck.Dao.RekamMedisDao;
import com.progmob.medcheck.Dao.ResepDao;
import com.progmob.medcheck.Model.Dokter;
import com.progmob.medcheck.Model.History;
import com.progmob.medcheck.Model.Obat;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.Model.RekamMedis;
import com.progmob.medcheck.Model.Resep;


@Database(entities = {Dokter.class, Pasien.class, Obat.class, Resep.class, RekamMedis.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "db_medcheck";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Menginstansiasi database");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Mengambil database instance");
        return sInstance;
    }

    //TODO: Taruh DAO dibawah ini
    public abstract DokterDao dokterDao();
    public abstract PasienDao pasienDao();
    public abstract ObatDao obatDao();
    public abstract RekamMedisDao rekamMedisDao();
    public abstract ResepDao resepDao();

}

