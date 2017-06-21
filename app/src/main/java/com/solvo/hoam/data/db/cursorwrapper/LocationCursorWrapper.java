package com.solvo.hoam.data.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.solvo.hoam.data.db.model.LocationModel;
import com.solvo.hoam.data.db.table.LocationTable;

public class LocationCursorWrapper extends CursorWrapper {

    public LocationCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public LocationModel getLocation() {
        return new LocationModel(
                getString(getColumnIndex(LocationTable.ID)),
                getString(getColumnIndex(LocationTable.NAME)),
                getString(getColumnIndex(LocationTable.REGION_ID)),
                getString(getColumnIndex(LocationTable.CREATED_AT)),
                getString(getColumnIndex(LocationTable.UPDATED_AT))
        );
    }
}
