package com.solvo.hoam.data.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.solvo.hoam.data.db.model.CategoryModel;
import com.solvo.hoam.data.db.table.CategoryTable;

public class CategoryCursorWrapper extends CursorWrapper {

    public CategoryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public CategoryModel getCategory() {
        return new CategoryModel(
                getString(getColumnIndex(CategoryTable.ID)),
                getString(getColumnIndex(CategoryTable.NAME)),
                getString(getColumnIndex(CategoryTable.SLUG)),
                getString(getColumnIndex(CategoryTable.PARENT_ID)),
                getInt(getColumnIndex(CategoryTable.PRIORITY)),
                getString(getColumnIndex(CategoryTable.CREATED_AT)),
                getString(getColumnIndex(CategoryTable.UPDATED_AT))
        );
    }
}
