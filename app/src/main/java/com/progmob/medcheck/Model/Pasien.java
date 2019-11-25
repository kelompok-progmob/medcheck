package com.progmob.medcheck.Model;

import java.util.Date;

public class Pasien {

    private String nama;
    private String jk;
    private String umur;
    private String created_at;

    public Pasien(String nama, String jk, String umur, String created_at) {
        this.nama = nama;
        this.jk = jk;
        this.umur = umur;
        this.created_at = created_at;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
