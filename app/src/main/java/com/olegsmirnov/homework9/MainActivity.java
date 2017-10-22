package com.olegsmirnov.homework9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.olegsmirnov.homework9.CreateEntries.MainCreateEntriesActivity;
import com.olegsmirnov.homework9.DeleteEntries.MainDeleteEntriesActivity;
import com.olegsmirnov.homework9.ReadEntries.MainReadEntriesActivity;
import com.olegsmirnov.homework9.UpdateEntries.UpdateOneEntryActivity;

public class MainActivity extends AppCompatActivity {

    Button bAddEntry;
    Button bDeleteEntry;
    Button bUpdateEntry;
    Button bShowEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bAddEntry = (Button) findViewById(R.id.activity_add_entry_button);
        bDeleteEntry = (Button) findViewById(R.id.activity_delete_entry_button);
        bUpdateEntry = (Button) findViewById(R.id.activity_update_entry_button);
        bShowEntry = (Button) findViewById(R.id.activity_read_entry_button);
        bAddEntry.setOnClickListener(mListener);
        bDeleteEntry.setOnClickListener(mListener);
        bUpdateEntry.setOnClickListener(mListener);
        bShowEntry.setOnClickListener(mListener);
    }
    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case (R.id.activity_add_entry_button):
                    intent = new Intent(getApplicationContext(), MainCreateEntriesActivity.class);
                    startActivity(intent);
                    break;
                case (R.id.activity_delete_entry_button):
                    intent = new Intent(getApplicationContext(), MainDeleteEntriesActivity.class);
                    startActivity(intent);
                    break;
                case (R.id.activity_update_entry_button):
                    intent = new Intent(getApplicationContext(), UpdateOneEntryActivity.class);
                    startActivity(intent);
                    break;
                case (R.id.activity_read_entry_button):
                    intent = new Intent(getApplicationContext(), MainReadEntriesActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}
