package com.progmob.medcheck.database;


import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.progmob.medcheck.Dao.DokterDao;
import com.progmob.medcheck.Dao.PasienDao;
import com.progmob.medcheck.Dao.RekamMedisDao;
import com.progmob.medcheck.Model.Dokter;
import com.progmob.medcheck.Model.Pasien;
import com.progmob.medcheck.Model.RekamMedis;


@Database(entities = {Dokter.class, Pasien.class}, version = 3, exportSchema = false)
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
//    public abstract RekamMedisDao rekamMedisDao();
    public abstract DokterDao dokterDao();
    public abstract PasienDao pasienDao();

}
