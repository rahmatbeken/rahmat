package com.souttab.kamus.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.souttab.kamus.Kamus;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

    private static final String _TABLE_KAMUS = "table_jaringan_komputer";

    private  SQLiteDatabase database;
    private CopyDatabase copyDatabase;


    String istilah, pengertian;

    public DatabaseUtil(Context context) {
        copyDatabase = new CopyDatabase(context);
    }

    public void open() { // open database and allow to write data
        database = copyDatabase.getReadableDatabase();
    }

    public void close() { // close database connection
        if (database != null) {
            database.close();
        }
    }

    // MAKANAN
    public  List<Kamus> listKamus() {
        List<Kamus> listKamus = new ArrayList<Kamus>();
        String query = "SELECT * FROM "+ _TABLE_KAMUS ;

        open();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            Kamus entity;
            do {
                entity = new Kamus();
                istilah = cursor.getString(cursor.getColumnIndex("istilah"));
                pengertian = cursor.getString(cursor.getColumnIndex("pengertian"));

                entity.setIstilah(istilah);
                entity.setPenjelasan(pengertian);

                listKamus.add(entity);
            } while (cursor.moveToNext());
        }
        close();
        return listKamus;
    }

    public Kamus getKamus(String istilah) {
        open();

        Cursor cursor = database.query(true, _TABLE_KAMUS, new String[]{"istilah",
                "pengertian"}, "istilah like ?", new String[]{"%"+istilah+"%"}, null, null, null,null);
        Kamus kamus = new Kamus();
        if (cursor != null) {
            cursor.moveToFirst();
            kamus.setIstilah(cursor.getString(cursor.getColumnIndex("istilah")));
            kamus.setPenjelasan(cursor.getString(cursor.getColumnIndex("pengertian")));
        }
        close();
        return kamus;
    }
 }
