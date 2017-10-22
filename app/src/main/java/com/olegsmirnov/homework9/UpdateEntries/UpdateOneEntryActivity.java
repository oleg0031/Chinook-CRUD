package com.olegsmirnov.homework9.UpdateEntries;

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

public class UpdateOneEntryActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    Button bUpdateEntry;
    EditText etIdToUpdate;
    EditText etUpdatePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_one_entry);
        mDbHelper = new HotelDbHelper(this);
        etIdToUpdate = (EditText) findViewById(R.id.et_update_one_id);
        etUpdatePhone = (EditText) findViewById(R.id.et_update_one_age);
        bUpdateEntry = (Button) findViewById(R.id.button_update_one_entry);
        bUpdateEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateOneEntry();
            }
        });
    }

    private void updateOneEntry() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String whereClause = HotelContract.GuestEntry._ID + " = ?";
        String[] whereArgs = {etIdToUpdate.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(HotelContract.GuestEntry.COLUMN_PHONE, etUpdatePhone.getText().toString());
        db.update(HotelContract.GuestEntry.TABLE_NAME, values, whereClause, whereArgs);
        Toast.makeText(getApplicationContext(), "Номер телефона гостя c ID " + etIdToUpdate.getText().toString() +
                " теперь " + etUpdatePhone.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
