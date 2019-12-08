package com.progmob.medcheck.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "tb_pasien")
public class Pasien {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="id_pasien")
    public int pasienId;
    @ColumnInfo(name="nama_pasien")
    public String namaPasien;
    @ColumnInfo(name="jenis_kelamin")
    public String gender;
    @ColumnInfo(name="tanggal_lahir")
    public String tglLahir;
    @ColumnInfo(name="created_at")
    public String createdAt;

    @Ignore
    public Pasien(String namaPasien, String gender, String tglLahir, String createdAt) {
        this.namaPasien = namaPasien;
        this.gender = gender;
        this.tglLahir = tglLahir;
        this.createdAt = createdAt;
    }

    public Pasien(int pasienId, String namaPasien, String gender, String tglLahir, String createdAt) {
        this.pasienId = pasienId;
        this.namaPasien = namaPasien;
        this.gender = gender;
        this.tglLahir = tglLahir;
        this.createdAt = createdAt;
    }

    public int getPasienId() {
        return pasienId;
    }

    public void setPasienId(int pasienId) {
        this.pasienId = pasienId;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
