package com.progmob.medcheck.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.progmob.medcheck.Model.RekamMedis;
import com.progmob.medcheck.Model.RekamMedisWithRelation;

import java.util.List;

@Dao
public interface RekamMedisDao {

    @Query("SELECT * FROM tb_rekam_medis ORDER BY id_rekam_medis")
    List<RekamMedisWithRelation> loadAllRekam();

    @Insert
    void insertRekam(RekamMedis rekamMedis);

    @Query("SELECT * FROM tb_rekam_medis WHERE id_rekam_medis = :id_rekam_medis")
    RekamMedisWithRelation loadBukuById(int id_rekam_medis);
}
