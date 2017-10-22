package com.olegsmirnov.homework9.DeleteEntries;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.olegsmirnov.homework9.HotelContract;
import com.olegsmirnov.homework9.HotelDbHelper;
import com.olegsmirnov.homework9.R;

public class MainDeleteEntriesActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    Button bDeleteOneEntry;
    Button bDeleteAllEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_entries_main);
        mDbHelper = new HotelDbHelper(this);
        bDeleteOneEntry = (Button) findViewById(R.id.button_delete_one_entry);
        bDeleteAllEntries = (Button) findViewById(R.id.button_delete_all_entries);
        bDeleteOneEntry.setOnClickListener(mListener);
        bDeleteAllEntries.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case (R.id.button_delete_one_entry):
                    intent = new Intent(getApplicationContext(), DeleteOneEntryActivity.class);
                    startActivity(intent);
                    break;
                case (R.id.button_delete_all_entries):
                    deleteAllGuests();
                    Toast.makeText(getApplicationContext(), "Все гости удалены", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void deleteAllGuests() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(HotelContract.GuestEntry.TABLE_NAME, null, null);
    }
}
