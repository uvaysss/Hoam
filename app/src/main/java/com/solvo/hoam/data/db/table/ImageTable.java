package com.solvo.hoam.data.db.table;

public class ImageTable {

    public static final String TABLE_NAME = "image";
    public static final String ID = "id";
    public static final String AD_ID = "ad_id";
    public static final String IS_MAIN = "is_main";
    public static final String BIG = "big";
    public static final String SMALL = "small";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            ID + " INTEGER NOT NULL PRIMARY KEY, " +
            AD_ID + " TEXT NOT NULL UNIQUE, " +
            IS_MAIN + " INTEGER NOT NULL, " +
            BIG + " TEXT NOT NULL, " +
            SMALL + " TEXT NOT NULL, " +
            CREATED_AT + " TEXT NOT NULL, " +
            UPDATED_AT + " TEXT NOT NULL);";
}
