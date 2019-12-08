package com.progmob.medcheck.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_dokter")
public class Dokter {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="dokter_id")
    public int dokterId;
    @ColumnInfo(name="nama_dokter")
    public String namaDokter;
    @ColumnInfo(name="spesialisasi")
    public String spesialisasi;
    @ColumnInfo(name="username")
    public String username;
    @ColumnInfo(name="password")
    public String password;

    @Ignore
    public Dokter(String namaDokter, String spesialisasi, String username, String password) {
        this.namaDokter = namaDokter;
        this.spesialisasi = spesialisasi;
        this.username = username;
        this.password = password;
    }

    public Dokter(int dokterId, String namaDokter, String spesialisasi, String username, String password) {
        this.dokterId = dokterId;
        this.namaDokter = namaDokter;
        this.spesialisasi = spesialisasi;
        this.username = username;
        this.password = password;
    }

    public int getDokterId() {
        return dokterId;
    }

    public void setDokterId(int dokterId) {
        this.dokterId = dokterId;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
