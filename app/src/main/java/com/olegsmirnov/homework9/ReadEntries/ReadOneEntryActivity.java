package com.olegsmirnov.homework9.ReadEntries;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.olegsmirnov.homework9.HotelContract;
import com.olegsmirnov.homework9.HotelDbHelper;
import com.olegsmirnov.homework9.R;

public class ReadOneEntryActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    Button bShowPersonInfo;
    EditText etReadId;
    TextView tvPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_one_entry);
        mDbHelper = new HotelDbHelper(this);
        tvPersonInfo = (TextView) findViewById(R.id.tv_read_person_info);
        etReadId = (EditText) findViewById(R.id.et_read_id);
        bShowPersonInfo = (Button) findViewById(R.id.button_read_show_person_info);
        bShowPersonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPersonInfo.setText("");
                readOnePersonInfo();
            }
        });
    }

    private void readOnePersonInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String selection = HotelContract.GuestEntry._ID + " = ?";
        String[] selectionArgs = {etReadId.getText().toString()};

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
                selection,             // столбцы для условия WHERE
                selectionArgs,         // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                 // порядок сортировки

        try {

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
                tvPersonInfo.append(("\nПосетитель №" + currentID + "\n\n" +
                        "Имя: " + currentFirstName + "\n" +
                        "Фамилия: " + currentLastName + "\n" +
                        "Компания: " + currentCompany + "\n" +
                        "Адрес: " + currentAddress + "\n" +
                        "Город: " + currentCity + " \n" +
                        "Страна: " + currentCountry + "\n" +
                        "Номер телефона: " + currentPhone + "\n" +
                        "Email: " + currentEmail));
            }
        } finally {
            cursor.close();
        }
    }
}
