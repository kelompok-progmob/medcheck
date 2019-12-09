package com.progmob.medcheck.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_obat")
public class Obat {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="id_obat")
    public int obatId;
    @ColumnInfo(name="nama")
    public String namaObat;
    @ColumnInfo(name="stok")
    public int stok;

    @Ignore
    public Obat(String namaObat, int stok) {
        this.namaObat = namaObat;
        this.stok = stok;
    }

    public Obat(int obatId, String namaObat, int stok) {
        this.obatId = obatId;
        this.namaObat = namaObat;
        this.stok = stok;
    }

    public int getObatId() {
        return obatId;
    }

    public void setObatId(int obatId) {
        this.obatId = obatId;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
