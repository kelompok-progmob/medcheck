package com.progmob.medcheck.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.progmob.medcheck.Model.RekamMedis;
import com.progmob.medcheck.Model.RekamMedisWithRelation;
import com.progmob.medcheck.Model.Resep;
import com.progmob.medcheck.Model.ResepWithRelations;

import java.util.List;

@Dao
public interface ResepDao {

    @Transaction
    @Query("SELECT * FROM tb_resep ORDER BY id_resep")
    List<ResepWithRelations> loadAllResep();

    @Insert
    void insertResep(Resep resep);

    @Transaction
    @Query("SELECT * FROM tb_resep WHERE id_rekam_medis = :id_rekam_medis")
    List<ResepWithRelations> getResepByIdRekamMedis(int id_rekam_medis);

    @Transaction
    @Query("SELECT * FROM tb_resep WHERE id_resep = :id_resep")
    ResepWithRelations loadResepById(int id_resep);
}
