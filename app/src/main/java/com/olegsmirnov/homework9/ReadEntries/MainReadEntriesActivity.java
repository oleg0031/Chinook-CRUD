package com.olegsmirnov.homework9.ReadEntries;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.olegsmirnov.homework9.HotelContract;
import com.olegsmirnov.homework9.HotelDbHelper;
import com.olegsmirnov.homework9.R;

public class MainReadEntriesActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    Button bReadOneEntry;
    Button bReadAllEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_entries_main);
        mDbHelper = new HotelDbHelper(this);
        bReadOneEntry = (Button) findViewById(R.id.button_read_one_entry);
        bReadAllEntries = (Button) findViewById(R.id.button_read_all_entries);
        bReadOneEntry.setOnClickListener(mListener);
        bReadAllEntries.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case (R.id.button_read_one_entry):
                    intent = new Intent(getApplicationContext(), ReadOneEntryActivity.class);
                    startActivity(intent);
                    break;
                case (R.id.button_read_all_entries):
                    intent = new Intent(getApplicationContext(), ReadAllEntriesActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
