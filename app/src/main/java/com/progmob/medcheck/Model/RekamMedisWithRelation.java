package com.progmob.medcheck.Model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RekamMedisWithRelation {

    @Embedded
    public RekamMedis rekamMedis;

    @Relation(parentColumn = "dokter_id", entityColumn = "dokter_id", entity = Dokter.class)
    public List<Dokter> dokters;

    @Relation(parentColumn = "id_pasien", entityColumn = "id_pasien", entity = Pasien.class)
    public List<Pasien> pasiens;

}
