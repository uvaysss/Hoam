package com.solvo.hoam.data.repository.datasource;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.solvo.hoam.data.db.cursorwrapper.ImageCursorWrapper;
import com.solvo.hoam.data.db.model.ImageModel;
import com.solvo.hoam.data.db.table.ImageTable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ImageDataSource {

    private SQLiteDatabase sqLiteDatabase;

    @Inject
    public ImageDataSource(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    private ContentValues buildContentValues(ImageModel image) {
        ContentValues values = new ContentValues();
        values.put(ImageTable.ID, image.getId());
        values.put(ImageTable.AD_ID, image.getAdId());
        values.put(ImageTable.IS_MAIN, image.getIsMain() ? 1 : 0);
        values.put(ImageTable.BIG, image.getBig());
        values.put(ImageTable.SMALL, image.getSmall());
        values.put(ImageTable.CREATED_AT, image.getCreatedAt());
        values.put(ImageTable.UPDATED_AT, image.getUpdatedAt());
        return values;
    }

    private ImageCursorWrapper query(String whereClause, String[] whereArgs) {
        return new ImageCursorWrapper(sqLiteDatabase.query(
                ImageTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        ));
    }

    public void saveImages(List<ImageModel> images) {
        for (ImageModel image : images) {
//            sqLiteDatabase.insert(ImageTable.TABLE_NAME, null, buildContentValues(image), SQLiteDatabase.CONFLICT_REPLACE);
            sqLiteDatabase.insert(ImageTable.TABLE_NAME, null, buildContentValues(image));
        }
    }

    public List<ImageModel> getImages() {
        List<ImageModel> imageList = new ArrayList<>();

        ImageCursorWrapper cursor = query(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                imageList.add(cursor.getImage());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return imageList;
    }

    public List<ImageModel> getImagesByAdId(String adId) {
        List<ImageModel> imageList = new ArrayList<>();

        ImageCursorWrapper cursor = query(
                ImageTable.AD_ID + " = ?",
                new String[]{adId}
        );

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                imageList.add(cursor.getImage());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return imageList;
    }

    public void deleteImages(List<ImageModel> imageList) {
        for (ImageModel image : imageList) {
            sqLiteDatabase.delete(ImageTable.TABLE_NAME, ImageTable.ID + " = ?", new String[]{image.getId()});
        }
    }
}
