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

    @Query("UPDATE tb_obat SET stok = stok - :jumlah WHERE id_obat = :id_obat")
    void reduceStok(int id_obat, int jumlah);

    @Query("SELECT * FROM tb_obat WHERE id_obat = :id_obat")
    Obat loadObatById(int id_obat);
}
