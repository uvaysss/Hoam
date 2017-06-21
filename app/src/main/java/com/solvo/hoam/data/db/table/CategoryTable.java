package com.solvo.hoam.data.db.table;

public class CategoryTable {

    public static final String TABLE_NAME = "category";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SLUG = "slug";
    public static final String PARENT_ID = "parent_id";
    public static final String PRIORITY = "priority";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            ID + " INTEGER NOT NULL PRIMARY KEY, " +
            NAME + " TEXT NOT NULL, " +
            SLUG + " TEXT NOT NULL, " +
            PARENT_ID + " TEXT, " +
            PRIORITY + " INTEGER NOT NULL, " +
            CREATED_AT + " TEXT NOT NULL, " +
            UPDATED_AT + " TEXT NOT NULL);";
}
