package com.example.drivingtest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Cursor QueryGetData(String str){
        SQLiteDatabase sql =getReadableDatabase();
        return sql.rawQuery(str,null);
    }

    public void QuerySetData(String str){
        SQLiteDatabase sql = getWritableDatabase();
        sql.execSQL(str);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
