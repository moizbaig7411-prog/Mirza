package com.qcsitemanager.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Db extends SQLiteOpenHelper {
    private static final String DB_NAME = "qc_site_manager.db";
    private static final int DB_VERSION = 1;
    public Db(Context context) { super(context, DB_NAME, null, DB_VERSION); }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE sor (id INTEGER PRIMARY KEY AUTOINCREMENT, code TEXT, location TEXT, category TEXT, title TEXT, details TEXT, due TEXT, created TEXT, status TEXT, photo TEXT, qc_remark TEXT)");
        db.execSQL("CREATE TABLE inspections (id INTEGER PRIMARY KEY AUTOINCREMENT, location TEXT, category TEXT, checklist TEXT, status TEXT, remark TEXT, date TEXT, photo TEXT)");
        db.execSQL("CREATE TABLE cubes (id INTEGER PRIMARY KEY AUTOINCREMENT, element TEXT, grade TEXT, cast_date TEXT, test7_date TEXT, strength7 TEXT, test28_date TEXT, strength28 TEXT, lab TEXT, remark TEXT, status TEXT)");
        db.execSQL("CREATE TABLE documents (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, category TEXT, reference TEXT, note TEXT, date TEXT)");
    }
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public Cursor count(String table, String where) {
        if (!allowed(table)) throw new IllegalArgumentException("Unknown table");
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT COUNT(*) FROM " + table + (where == null || where.trim().isEmpty() ? "" : " WHERE " + safeWhere(table, where)), null);
    }
    private String safeWhere(String table, String where) {
        if (table.equals("sor") && where.equals("status!='Closed'")) return where;
        throw new IllegalArgumentException("Unsupported filter");
    }
    public Cursor all(String table) {
        if (!allowed(table)) throw new IllegalArgumentException("Unknown table");
        return getReadableDatabase().query(table, null, null, null, null, null, "id DESC");
    }
    public long add(String table, ContentValues values) {
        if (!allowed(table)) throw new IllegalArgumentException("Unknown table");
        return getWritableDatabase().insertOrThrow(table, null, values);
    }
    public int update(String table, ContentValues values, String id) {
        if (!allowed(table)) throw new IllegalArgumentException("Unknown table");
        return getWritableDatabase().update(table, values, "id=?", new String[]{id});
    }
    private boolean allowed(String table) {
        return "sor".equals(table) || "inspections".equals(table) || "cubes".equals(table) || "documents".equals(table);
    }
}
