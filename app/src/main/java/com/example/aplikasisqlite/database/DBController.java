package com.example.aplikasisqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {

    public DBController(Context context) {
        super(context, "ProdiTI", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE teman (id integer primary key, nama text, telpon text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE if exists teman");
        onCreate(db);
    }
    public void  insertData(HashMap<String,String> queryValues){

        // Variable SQLiteDatabase
        SQLiteDatabase basisdata = this.getWritableDatabase();

        ContentValues nilai = new ContentValues();
        nilai.put("nama",queryValues.get("nama"));
        nilai.put("telpon", queryValues.get("telpon"));

        basisdata.insert("teman",null,nilai);
        basisdata.close();
    }
    public ArrayList<HashMap<String,String>> getAllTeman() {
        ArrayList<HashMap<String, String>> daftarTeman;
        daftarTeman = new ArrayList<HashMap<String, String>>();

        String selectQuery = " SELECT * FROM teman";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("nama", cursor.getString(1));
                map.put("telpon", cursor.getString(2));
                daftarTeman.add(map);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return daftarTeman;
    }
}
