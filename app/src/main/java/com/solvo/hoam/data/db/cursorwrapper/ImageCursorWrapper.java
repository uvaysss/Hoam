package com.solvo.hoam.data.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.solvo.hoam.data.db.model.ImageModel;
import com.solvo.hoam.data.db.table.ImageTable;

public class ImageCursorWrapper extends CursorWrapper {

    public ImageCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ImageModel getImage() {
        return new ImageModel(
                getString(getColumnIndex(ImageTable.ID)),
                getString(getColumnIndex(ImageTable.AD_ID)),
                getInt(getColumnIndex(ImageTable.IS_MAIN)) != 0,
                getString(getColumnIndex(ImageTable.BIG)),
                getString(getColumnIndex(ImageTable.SMALL)),
                getString(getColumnIndex(ImageTable.CREATED_AT)),
                getString(getColumnIndex(ImageTable.UPDATED_AT))
        );
    }
}
