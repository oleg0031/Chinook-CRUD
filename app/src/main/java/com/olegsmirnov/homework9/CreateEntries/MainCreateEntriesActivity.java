package com.olegsmirnov.homework9.CreateEntries;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.olegsmirnov.homework9.HotelContract;
import com.olegsmirnov.homework9.HotelDbHelper;
import com.olegsmirnov.homework9.R;

public class MainCreateEntriesActivity extends AppCompatActivity {

    EditText etFirstName;
    EditText etLastName;
    EditText etCompany;
    EditText etAddress;
    EditText etCity;
    EditText etCountry;
    EditText etPhone;
    EditText etEmail;
    Button bAddOneEntry;
    Button bAddAndBackOneEntry;
    HotelDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entries_main);
        mDbHelper = new HotelDbHelper(this);
        etFirstName = (EditText) findViewById(R.id.et_create_first_name);
        etLastName = (EditText) findViewById(R.id.et_create_last_name);
        etCompany = (EditText) findViewById(R.id.et_create_company);
        etAddress = (EditText) findViewById(R.id.et_create_address);
        etCity = (EditText) findViewById(R.id.et_create_city);
        etCountry = (EditText) findViewById(R.id.et_create_country);
        etPhone = (EditText) findViewById(R.id.et_create_phone);
        etEmail = (EditText) findViewById(R.id.et_create_email);
        bAddOneEntry = (Button) findViewById(R.id.button_add_entry);
        bAddAndBackOneEntry = (Button) findViewById(R.id.button_add_entry_and_back);
        bAddOneEntry.setOnClickListener(mListener);
        bAddAndBackOneEntry.setOnClickListener(mListener);
    }

    private void insertGuest(String firstName, String lastName, String company, String address,
                             String city, String country, String phone, String email) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HotelContract.GuestEntry.COLUMN_FIRST_NAME, firstName);
        values.put(HotelContract.GuestEntry.COLUMN_LAST_NAME, lastName);
        values.put(HotelContract.GuestEntry.COLUMN_COMPANY, company);
        values.put(HotelContract.GuestEntry.COLUMN_ADDRESS, address);
        values.put(HotelContract.GuestEntry.COLUMN_CITY, city);
        values.put(HotelContract.GuestEntry.COLUMN_COUNTRY, country);
        values.put(HotelContract.GuestEntry.COLUMN_PHONE, phone);
        values.put(HotelContract.GuestEntry.COLUMN_EMAIL, email);
        db.insert(HotelContract.GuestEntry.TABLE_NAME, null, values);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String company = etCompany.getText().toString();
            String address = etAddress.getText().toString();
            String city = etCity.getText().toString();
            String country = etCountry.getText().toString();
            String phone = etPhone.getText().toString();
            String email = etEmail.getText().toString();

            switch (view.getId()) {
                case (R.id.button_add_entry):
                    if (firstName.isEmpty() || lastName.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Укажите имя и фамилию", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        insertGuest(firstName, lastName, company, address, city, country, phone, email);
                        Toast.makeText(getApplicationContext(), "Гость " + firstName + " " + lastName + " успешно добавлен(а)", Toast.LENGTH_SHORT).show();
                        etFirstName.setText("");
                        etLastName.setText("");
                        etCompany.setText("");
                        etAddress.setText("");
                        etCity.setText("");
                        etCountry.setText("");
                        etPhone.setText("");
                        etEmail.setText("");
                        break;
                    }
                case (R.id.button_add_entry_and_back):
                    if (firstName.isEmpty() || lastName.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Укажите имя и фамилию", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        insertGuest(firstName, lastName, company, address, city, country, phone, email);
                        Toast.makeText(getApplicationContext(), "Гость " + firstName + " " + lastName + " успешно добавлен(а)", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                        break;
                    }
            }
        }
    };
}
