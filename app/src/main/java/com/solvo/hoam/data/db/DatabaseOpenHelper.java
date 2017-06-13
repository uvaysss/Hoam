package com.solvo.hoam.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.solvo.hoam.data.db.table.AdTable;
import com.solvo.hoam.data.db.table.CategoryTable;
import com.solvo.hoam.data.db.table.ImageTable;
import com.solvo.hoam.data.db.table.LocationTable;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hoam";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoryTable.SQL_CREATE_TABLE);
        db.execSQL(LocationTable.SQL_CREATE_TABLE);
        db.execSQL(AdTable.SQL_CREATE_TABLE);
        db.execSQL(ImageTable.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
