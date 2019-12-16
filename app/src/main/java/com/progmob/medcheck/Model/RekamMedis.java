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
    @ColumnInfo(name="tekanan_darah")
    public String tekananDarah;
    @ColumnInfo(name="suhu_badan")
    public int suhuBadan;
    @ColumnInfo(name="berat_badan")
    public int beratBadan;
    @ColumnInfo(name="tinggi_badan")
    public int tinggiBadan;
    @ColumnInfo(name="diagnosa_penyakit")
    public String diagnosaPenyakit;

    //lanjutin sendiri ya

    @Ignore
    public RekamMedis(int dokterId, int pasienId, String keluhan, String tekananDarah, int suhuBadan, int beratBadan, int tinggiBadan, String diagnosaPenyakit) {
        this.dokterId = dokterId;
        this.pasienId = pasienId;
        this.keluhan = keluhan;
        this.tekananDarah = tekananDarah;
        this.suhuBadan = suhuBadan;
        this.beratBadan = beratBadan;
        this.tinggiBadan = tinggiBadan;
        this.diagnosaPenyakit = diagnosaPenyakit;
    }

    @Ignore
    public RekamMedis() {
    }

    public RekamMedis(int rekamId, int dokterId, int pasienId, String keluhan, String tekananDarah, int suhuBadan, int beratBadan, int tinggiBadan, String diagnosaPenyakit) {
        this.rekamId = rekamId;
        this.dokterId = dokterId;
        this.pasienId = pasienId;
        this.keluhan = keluhan;
        this.tekananDarah = tekananDarah;
        this.suhuBadan = suhuBadan;
        this.beratBadan = beratBadan;
        this.tinggiBadan = tinggiBadan;
        this.diagnosaPenyakit = diagnosaPenyakit;
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

    public String getTekananDarah() {
        return tekananDarah;
    }

    public void setTekananDarah(String tekananDarah) {
        this.tekananDarah = tekananDarah;
    }

    public int getSuhuBadan() {
        return suhuBadan;
    }

    public void setSuhuBadan(int suhuBadan) {
        this.suhuBadan = suhuBadan;
    }

    public int getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(int beratBadan) {
        this.beratBadan = beratBadan;
    }

    public int getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(int tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public String getDiagnosaPenyakit() {
        return diagnosaPenyakit;
    }

    public void setDiagnosaPenyakit(String diagnosaPenyakit) {
        this.diagnosaPenyakit = diagnosaPenyakit;
    }
}
