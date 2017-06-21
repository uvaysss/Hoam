package com.solvo.hoam.data.repository.datasource;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.solvo.hoam.data.db.cursorwrapper.CategoryCursorWrapper;
import com.solvo.hoam.data.db.model.CategoryModel;
import com.solvo.hoam.data.db.table.CategoryTable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CategoryDataSource {

    private SQLiteDatabase sqLiteDatabase;

    @Inject
    public CategoryDataSource(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    private ContentValues buildContentValues(CategoryModel category) {
        ContentValues values = new ContentValues();
        values.put(CategoryTable.ID, category.getId());
        values.put(CategoryTable.NAME, category.getName());
        values.put(CategoryTable.SLUG, category.getSlug());
        values.put(CategoryTable.PARENT_ID, category.getParentId());
        values.put(CategoryTable.PRIORITY, category.getPriority());
        values.put(CategoryTable.CREATED_AT, category.getCreatedAt());
        values.put(CategoryTable.UPDATED_AT, category.getUpdatedAt());
        return values;
    }

    private CategoryCursorWrapper query(String whereClause, String[] whereArgs) {
        return new CategoryCursorWrapper(sqLiteDatabase.query(
                CategoryTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        ));
    }

    public void saveCategories(List<CategoryModel> categories) {
        for (CategoryModel category : categories) {
            saveCategory(category);
        }
    }

    public void saveCategory(CategoryModel category) {
        sqLiteDatabase.insertWithOnConflict(CategoryTable.TABLE_NAME, null, buildContentValues(category), SQLiteDatabase.CONFLICT_REPLACE);
    }

    public List<CategoryModel> getCategories() {
        List<CategoryModel> categoryList = new ArrayList<>();

        CategoryCursorWrapper cursor = query(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                categoryList.add(cursor.getCategory());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return categoryList;
    }

    public CategoryModel getCategory(String categoryId) {
        CategoryCursorWrapper cursor = query(
                CategoryTable.ID + " = ?",
                new String[] { categoryId });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();

            CategoryModel category = cursor.getCategory();

            return category;
        } finally {
            cursor.close();
        }
    }
}
