package com.olegsmirnov.homework9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.olegsmirnov.homework9.HotelContract.GuestEntry;

public class HotelDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public HotelDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + GuestEntry.TABLE_NAME + " ("
                + HotelContract.GuestEntry._ID + " INTEGER PRIMARY KEY, "
                + GuestEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL, "
                + GuestEntry.COLUMN_LAST_NAME + " TEXT NOT NULL, "
                + GuestEntry.COLUMN_COMPANY + " TEXT, "
                + GuestEntry.COLUMN_ADDRESS + " TEXT, "
                + GuestEntry.COLUMN_CITY + " TEXT, "
                + GuestEntry.COLUMN_COUNTRY + " TEXT, "
                + GuestEntry.COLUMN_PHONE + " TEXT, "
                + GuestEntry.COLUMN_EMAIL + " TEXT);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        String UPDATE_TABLE = "DROP TABLE IF EXISTS " + GuestEntry.TABLE_NAME + ";";
        db.execSQL(UPDATE_TABLE);
    }
}