package com.mayank13059.dome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayank Vachher (2013059) on 09/11/16.
 */

public class SQLDB {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_TASKTITLE = "title";
    public static final String KEY_TASKDEETS = "deets";
    public static final String KEY_TASKSTATUS = "status";

    private static final String DATABASE_NAME = "DomeDB";

    private static final String TABLE_NAME = "TaskList";
    private static final int DATABASE_VERSION = 1;

    private DbHelper mHelper;
    private final Context mContext;
    private SQLiteDatabase mDatabase_read;
    private SQLiteDatabase mDatabase_write;

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        //Set up database here
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                    //Column name     Type of variable
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_TASKTITLE + " TEXT NOT NULL, " +
                    KEY_TASKDEETS + " TEXT NOT NULL, " +
                    KEY_TASKSTATUS + " BIT NOT NULL);"
            );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

    }

    public SQLDB(Context c) {
        mContext = c;
    }

    public SQLDB open() throws SQLException {
        mHelper = new DbHelper (mContext);
        mDatabase_write = mHelper.getWritableDatabase();
        mDatabase_read = mHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        mHelper.close();
    }

    public long createEntry(String title, String deets, Integer status) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_TASKTITLE, title);
        cv.put(KEY_TASKDEETS, deets);
        cv.put(KEY_TASKSTATUS, status);
        return mDatabase_write.insert(TABLE_NAME, null, cv);
    }

    public List<Task> readAll() {

        List<Task> fileList = new ArrayList<Task>();

        String[] projection = {
                KEY_ROWID,
                KEY_TASKTITLE,
                KEY_TASKDEETS,
                KEY_TASKSTATUS
        };

        String sortOrder = KEY_ROWID;

        Cursor cursor = mDatabase_read.query(
                TABLE_NAME, // The table to query
                projection, // The columns to return
                null, // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                sortOrder // The sort order
        );

        if (cursor.moveToFirst()) {
            do {
                Task entry = new Task();
                entry.setTitle(cursor.getString(1));
                entry.setDeets(cursor.getString(2));
                entry.setStatus(Integer.parseInt(cursor.getString(3)));
                fileList.add(entry);
            } while (cursor.moveToNext());
        }

        return fileList;
    }

    public void deleteEntry(String fileName) {
        mDatabase_write.delete(TABLE_NAME, KEY_TASKTITLE + " = ?",
                new String[] { fileName });
    }

    public void changeStatus(String toSearch, Integer toReplaceWith) {
        ContentValues values = new ContentValues();

        values.put(KEY_TASKSTATUS, toReplaceWith);

        mDatabase_write.update(TABLE_NAME, values, KEY_TASKTITLE + " = ?",
                new String[] { toSearch });
    }
}