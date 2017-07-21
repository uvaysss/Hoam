package com.solvo.hoam.data.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import com.solvo.hoam.BuildConfig;
import com.solvo.hoam.data.db.table.AdTable;
import com.solvo.hoam.data.db.table.CategoryTable;
import com.solvo.hoam.data.db.table.ImageTable;
import com.solvo.hoam.data.db.table.LocationTable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.JELLY_BEAN)
public class DatabaseOpenHelperTest {

    private DatabaseOpenHelper databaseOpenHelper;

    @Before
    public void setUp() throws Exception {
        databaseOpenHelper = new DatabaseOpenHelper(RuntimeEnvironment.application);
    }

    @After
    public void tearDown() throws Exception {
        databaseOpenHelper.close();
    }

    @Test
    public void testOnCreate(){
        assertTrue(existTable(CategoryTable.TABLE_NAME));
        assertTrue(existTable(LocationTable.TABLE_NAME));
        assertTrue(existTable(AdTable.TABLE_NAME));
        assertTrue(existTable(ImageTable.TABLE_NAME));
    }

    public boolean existTable(String name) {
        SQLiteDatabase database = databaseOpenHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT DISTINCT TABLE_NAME FROM SQLITE_MASTER WHERE TABLE_NAME = '" + name + "'", null);

        boolean exist = false;

        if (cursor != null) {
            exist = cursor.getCount() > 0;
            cursor.close();
        }

        return exist;
    }
}