package com.progmob.medcheck.Model;


import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_rekam_medis")
public class RekamMedis {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="id_rekam_medis")
    public int rekamId;
    @ColumnInfo(name="dokter_id")
    public int dokterId;
    @ColumnInfo(name="id_pasien")
    public int pasienId;
    @ColumnInfo(name="keluhan")
    public String keluhan;

    //lanjutin sendiri ya

    @Ignore
    public RekamMedis(int dokterid, int pasienid, String keluhan) {
        this.dokterId = dokterid;
        this.pasienId = pasienid;
        this.keluhan = keluhan;
    }

    public int getRekamId() {
        return rekamId;
    }

    public void setRekamId(int rekamId) {
        this.rekamId = rekamId;
    }

    public int getDokterId() {
        return dokterId;
    }

    public void setDokterId(int dokterId) {
        this.dokterId = dokterId;
    }

    public int getPasienId() {
        return pasienId;
    }

    public void setPasienId(int pasienId) {
        this.pasienId = pasienId;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }
}
