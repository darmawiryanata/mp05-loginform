package com.example.latihan5_loginform;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class sql extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db.db";
    private static final int DATABASE_VERSION = 1;

    public sql(Context context) {
        //menjalankan konstruktor dari parent class
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE buku " +
                "(id integer primary key autoincrement," +
                "judul_buku text null," +
                "nama_pengarang text null," +
                "tahun_terbit text null," +
                "penerbit text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO buku " +
                "(id, judul_buku, nama_pengarang, tahun_terbit, penerbit) " +
                "VALUES (1, 'Penerapan Sistem', 'Jogiyanto', '2010', 'Andy');";
        db.execSQL(sql);
        sql = "INSERT INTO buku " +
                "(id, judul_buku, nama_pengarang, tahun_terbit, penerbit) " +
                "VALUES (2, 'Pengantar TI', 'Sutarman', '2012', 'Bumi Aksara');";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
}
