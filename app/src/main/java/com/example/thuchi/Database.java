package com.example.thuchi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "thuchi.sqlite";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS THUCHI(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TenKhoan TEXT, " +
                "NgayThang TEXT, " +
                "SoTien REAL, " +
                "IsThu INTEGER)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS THUCHI";
        db.execSQL(dropTableQuery);

        onCreate(db);
    }

    public void insertThuChi(String tenKhoan, String ngayThang, double soTien, boolean isThu) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("TenKhoan", tenKhoan);
        values.put("NgayThang", ngayThang);
        values.put("SoTien", soTien);
        values.put("IsThu", isThu ? 1 : 0);

        db.insert("THUCHI", null, values);
        db.close();
    }

//    public List<ThuChi> getAllThuChi() {
//        List<ThuChi> thuChiList = new ArrayList<>();
//
//        SQLiteDatabase db = getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("SELECT * FROM THUCHI", null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(cursor.getColumnIndex("id"));
//                String tenKhoan = cursor.getString(cursor.getColumnIndex("TenKhoan"));
//                String ngayThang = cursor.getString(cursor.getColumnIndex("NgayThang"));
//                double soTien = cursor.getDouble(cursor.getColumnIndex("SoTien"));
//                boolean isThu = cursor.getInt(cursor.getColumnIndex("IsThu")) == 1;
//
//                ThuChi thuChi = new ThuChi(id, tenKhoan, ngayThang, soTien, isThu);
//                thuChiList.add(thuChi);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//
//        return thuChiList;
//    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
}

