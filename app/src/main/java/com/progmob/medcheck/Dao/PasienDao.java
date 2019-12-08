package com.progmob.medcheck.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.progmob.medcheck.Model.Pasien;

import java.util.List;

@Dao
public interface PasienDao {

    @Query("SELECT * FROM tb_pasien ORDER BY id_pasien")
    List<Pasien> loadAllPasien();

    @Insert
    void insertPasien(Pasien pasien);

    @Query("SELECT * FROM tb_pasien WHERE id_pasien = :pasien_id")
    Pasien loadPasienById(int pasien_id);

}
