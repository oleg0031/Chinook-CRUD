package com.olegsmirnov.homework9.DeleteEntries;

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

import java.util.Arrays;

public class DeleteOneEntryActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    Button bDeleteEntry;
    EditText etDeleteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_one_entry);
        mDbHelper = new HotelDbHelper(this);
        etDeleteId = (EditText) findViewById(R.id.et_delete_one_id);
        bDeleteEntry = (Button) findViewById(R.id.button_delete_one_entry);
        bDeleteEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteOneGuest();
            }
        });
    }

    private void deleteOneGuest() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(HotelContract.GuestEntry.TABLE_NAME, HotelContract.GuestEntry._ID + " = ? ", new String[] {etDeleteId.getText().toString()});
        Toast.makeText(this, "Пользователь с ID " + etDeleteId.getText().toString() + "был удален", Toast.LENGTH_SHORT).show();
    }
}
