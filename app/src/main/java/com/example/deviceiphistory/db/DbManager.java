package com.example.deviceiphistory.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public DbManager(Context context) {
        this.dbHelper = new DbHelper(context);
    }

    public void openDb() {
        db = dbHelper.getWritableDatabase();
    }

    public void closeDb() {
        db.close();
    }

    public void insertIp(String ip) {
        ContentValues values = new ContentValues();
        values.put(DBConst.COLUMN_NAME_ADDRESS, ip);
        db.insert(DBConst.TABLE_NAME, null, values);
    }

    public List<String> readHistory() {
        List<String> listAddresses = new ArrayList<>();

        Cursor cursor = db.query(DBConst.TABLE_NAME,null, null,
                null, null,null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String address = cursor.getString(
                    cursor.getColumnIndex(DBConst.COLUMN_NAME_ADDRESS)
            );
            listAddresses.add(address);
        }
        cursor.close();
        return listAddresses;
    }
}
