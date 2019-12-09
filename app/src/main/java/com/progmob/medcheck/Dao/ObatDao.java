package com.progmob.medcheck.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.progmob.medcheck.Model.Obat;
import com.progmob.medcheck.Model.Pasien;

import java.util.List;

@Dao
public interface ObatDao {

    @Query("SELECT * FROM tb_obat ORDER BY id_obat")
    List<Obat> loadAllObat();

    @Insert
    void insertObat(Obat obat);

    @Update
    void updateObat(Obat obat);

    @Delete
    void deleteObat(Obat obat);

    @Query("SELECT * FROM tb_obat WHERE id_obat = :id_obat")
    Pasien loadObatById(int id_obat);
}
