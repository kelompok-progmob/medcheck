package com.progmob.medcheck.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_obat")
public class Obat {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="id_obat")
    public int obatId;
    @ColumnInfo(name="nama")
    public String namaObat;
    @ColumnInfo(name="stok")
    public String stok;

}
