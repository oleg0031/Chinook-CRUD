package com.olegsmirnov.homework9.ReadEntries;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.olegsmirnov.homework9.HotelContract;
import com.olegsmirnov.homework9.HotelDbHelper;
import com.olegsmirnov.homework9.R;

import java.util.Arrays;

public class ReadAllEntriesActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all_entries);
        mDbHelper = new HotelDbHelper(this);
        readAllDatabaseInfo();
    }

    private void readAllDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HotelContract.GuestEntry._ID,
                HotelContract.GuestEntry.COLUMN_FIRST_NAME,
                HotelContract.GuestEntry.COLUMN_LAST_NAME,
                HotelContract.GuestEntry.COLUMN_COMPANY,
                HotelContract.GuestEntry.COLUMN_ADDRESS,
                HotelContract.GuestEntry.COLUMN_CITY,
                HotelContract.GuestEntry.COLUMN_COUNTRY,
                HotelContract.GuestEntry.COLUMN_PHONE,
                HotelContract.GuestEntry.COLUMN_EMAIL};

        Cursor cursor = db.query(
                HotelContract.GuestEntry.TABLE_NAME, // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                 // порядок сортировки

        TextView displayTextView = (TextView) findViewById(R.id.tv_read_all_entries);

        try {
            displayTextView.setText("Таблица содержит " + cursor.getCount() + " гостей.\n\n");

            int idColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry._ID);
            int firstNameColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_FIRST_NAME);
            int lastNameColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_LAST_NAME);
            int companyColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_COMPANY);
            int addressColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_ADDRESS);
            int cityColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_CITY);
            int countryColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_COUNTRY);
            int phoneColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_PHONE);
            int emailColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_EMAIL);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentFirstName = cursor.getString(firstNameColumnIndex);
                String currentLastName = cursor.getString(lastNameColumnIndex);
                String currentCompany = cursor.getString(companyColumnIndex);
                String currentAddress = cursor.getString(addressColumnIndex);
                String currentCity = cursor.getString(cityColumnIndex);
                String currentCountry = cursor.getString(countryColumnIndex);
                String currentPhone = cursor.getString(phoneColumnIndex);
                String currentEmail = cursor.getString(emailColumnIndex);
                displayTextView.append(("\nПосетитель №" + currentID + "\n\n" +
                        "Имя: " + currentFirstName + "\n" +
                        "Фамилия: " + currentLastName + "\n" +
                        "Компания: " + currentCompany + "\n" +
                        "Адрес: " + currentAddress + "\n" +
                        "Город: " + currentCity + " \n" +
                        "Страна: " + currentCountry + "\n" +
                        "Номер телефона: " + currentPhone + "\n" +
                        "Email: " + currentEmail + "\n"));
            }
        } finally {
            cursor.close();
        }
    }
}
