package com.progmob.medcheck.Model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ResepWithRelations {

    @Embedded
    public Resep resep;

    @Relation(parentColumn = "id_rekam_medis", entityColumn = "id_rekam_medis", entity = RekamMedis.class)
    public List<RekamMedis> rekamMedis;

    @Relation(parentColumn = "id_obat", entityColumn = "id_obat", entity = Obat.class)
    public List<Obat> obats;

}
