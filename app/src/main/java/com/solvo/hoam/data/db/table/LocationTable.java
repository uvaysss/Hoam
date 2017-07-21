package com.solvo.hoam.data.db.table;

public class LocationTable {

    public static final String TABLE_NAME = "location";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String REGION_ID = "region_id";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            ID + " INTEGER NOT NULL PRIMARY KEY, " +
            NAME + " TEXT NOT NULL, " +
            REGION_ID + " TEXT NOT NULL, " +
            CREATED_AT + " TEXT NOT NULL, " +
            UPDATED_AT + " TEXT NOT NULL);";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
