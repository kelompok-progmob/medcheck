package com.progmob.medcheck.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;


import com.progmob.medcheck.Model.RekamMedis;
import com.progmob.medcheck.Model.RekamMedisWithRelation;

import java.util.List;

@Dao
public interface RekamMedisDao {

    @Transaction
    @Query("SELECT * FROM tb_rekam_medis ORDER BY id_rekam_medis")
    List<RekamMedisWithRelation> loadAllRekam();

    @Transaction
    @Query("SELECT max(id_rekam_medis) FROM tb_rekam_medis ORDER BY id_rekam_medis")
    int lastRekamMedisId();

    @Insert
    void insertRekam(RekamMedis rekamMedis);

    @Transaction
    @Query("SELECT count(*) FROM tb_rekam_medis WHERE strftime('%m', tanggal_rekam) = strftime('%m', CURRENT_DATE)")
    int getTotalPasienHariIni();

    @Transaction
    @Query("SELECT count(*) FROM tb_rekam_medis WHERE strftime('%Y', tanggal_rekam) = strftime('%Y', CURRENT_DATE) AND strftime('%m', tanggal_rekam) = strftime('%m', CURRENT_DATE)")
    int getTotalPasienBulanIni();

    @Transaction
    @Query("SELECT * FROM tb_rekam_medis WHERE id_rekam_medis = :id_rekam_medis")
    RekamMedisWithRelation loadRekamMedisById(int id_rekam_medis);


}
