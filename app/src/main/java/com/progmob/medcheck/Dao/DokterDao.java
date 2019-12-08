package com.progmob.medcheck.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.progmob.medcheck.Model.Dokter;

import java.util.List;

@Dao
public interface DokterDao {

    @Query("SELECT * FROM tb_dokter ORDER BY dokter_id")
    List<Dokter> loadAllDokter();

    @Insert
    void insertDokter(Dokter dokter);

    @Query("SELECT * FROM tb_dokter WHERE dokter_id = :dokter_id")
    Dokter loadDokterById(int dokter_id);

}
