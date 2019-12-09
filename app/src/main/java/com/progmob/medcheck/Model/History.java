package com.progmob.medcheck.Model;

import java.util.Date;


public class History {
    private String nama;
    private String keluhan;
    private String created_at;

    public History(String nama, String keluhan, String created_at) {
        this.nama = nama;
        this.keluhan = keluhan;
        this.created_at = created_at;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
