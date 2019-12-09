package com.progmob.medcheck.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_resep")
public class Resep {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="id_resep")
    public int idResep;
    @ColumnInfo(name="id_rekam_medis")
    public int idRekamMedis;
    @ColumnInfo(name="id_obat")
    public int idObat;
    @ColumnInfo(name="jumlah")
    public int jumlah;
    @ColumnInfo(name="keterangan")
    public String keterangan;

    @Ignore
    public Resep(int idRekamMedis, int idObat, int jumlah, String keterangan) {
        this.idRekamMedis = idRekamMedis;
        this.idObat = idObat;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    public Resep(int idResep, int idRekamMedis, int idObat, int jumlah, String keterangan) {
        this.idResep = idResep;
        this.idRekamMedis = idRekamMedis;
        this.idObat = idObat;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    public int getIdResep() {
        return idResep;
    }

    public void setIdResep(int idResep) {
        this.idResep = idResep;
    }

    public int getIdRekamMedis() {
        return idRekamMedis;
    }

    public void setIdRekamMedis(int idRekamMedis) {
        this.idRekamMedis = idRekamMedis;
    }

    public int getIdObat() {
        return idObat;
    }

    public void setIdObat(int idObat) {
        this.idObat = idObat;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
