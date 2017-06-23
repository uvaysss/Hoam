package com.solvo.hoam.data.repository.datasource;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.solvo.hoam.data.db.cursorwrapper.LocationCursorWrapper;
import com.solvo.hoam.data.db.model.LocationModel;
import com.solvo.hoam.data.db.table.LocationTable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LocationDataSource {

    private SQLiteDatabase sqLiteDatabase;

    @Inject
    public LocationDataSource(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    private ContentValues buildContentValues(LocationModel location) {
        ContentValues values = new ContentValues();
        values.put(LocationTable.ID, location.getId());
        values.put(LocationTable.NAME, location.getName());
        values.put(LocationTable.REGION_ID, location.getRegionId());
        values.put(LocationTable.CREATED_AT, location.getCreatedAt());
        values.put(LocationTable.UPDATED_AT, location.getUpdatedAt());
        return values;
    }

    private LocationCursorWrapper query(String whereClause, String[] whereArgs) {
        return new LocationCursorWrapper(sqLiteDatabase.query(
                LocationTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        ));
    }

    public void saveLocations(List<LocationModel> locations) {
        for (LocationModel location : locations) {
            saveLocation(location);
        }
    }

    public void saveLocation(LocationModel location) {
        sqLiteDatabase.insertWithOnConflict(LocationTable.TABLE_NAME, null, buildContentValues(location), SQLiteDatabase.CONFLICT_REPLACE);
    }

    public List<LocationModel> getLocations() {
        List<LocationModel> locationList = new ArrayList<>();

        LocationCursorWrapper cursor = query(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                locationList.add(cursor.getLocation());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return locationList;
    }

    public LocationModel getLocationById(String locationId) {
        LocationCursorWrapper cursor = query(
                LocationTable.ID + " = ?",
                new String[] { locationId });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();

            return cursor.getLocation();
        } finally {
            cursor.close();
        }
    }

    public LocationModel getLocationByName(String name) {
        LocationCursorWrapper cursor = query(
                LocationTable.NAME + " = ?",
                new String[] { name });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();

            return cursor.getLocation();
        } finally {
            cursor.close();
        }
    }
}
