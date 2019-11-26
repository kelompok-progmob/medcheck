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

    public String getHistoryNama() {
        return nama;
    }

    public void setHistoryNama(String nama) {
        this.nama = nama;
    }


    public String getHistoryKeluhan() {
        return keluhan;
    }

    public void setHistoryKeluhan(String keluhan) {
        this.keluhan= keluhan;
    }

    public String getHistoryCreated_at() {
        return created_at;
    }

    public void setHistoryCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
