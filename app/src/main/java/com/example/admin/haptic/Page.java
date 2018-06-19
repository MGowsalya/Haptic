package com.example.admin.haptic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Page extends AppCompatActivity {
 int st=0;
    SQLiteDatabase db;
    EditText pin;
    Button pin_ok_button;
    ArrayList arrayList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        db = getApplicationContext().openOrCreateDatabase("Master.db", Context.MODE_PRIVATE ,null);
        db.execSQL("create table if not exists LoginDetails(Username varchar,Moblienumber int,Pin int,Status int);");

        pin = findViewById(R.id.pin_edittext);
        pin_ok_button = findViewById(R.id.pin_ok_button);
        pin_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectionQuery = "Select Pin from LoginDetails";
                Cursor cursor = db.rawQuery(selectionQuery,null);
                if(cursor.moveToFirst()){
                    do{
                        String pin = cursor.getString(0);
                        arrayList.add(pin);
                        Toast.makeText(Page.this, "pin: "+pin, Toast.LENGTH_SHORT).show();
                    }while (cursor.moveToNext());
                }
                String get_pin = pin.getText().toString();
                if(arrayList.contains(get_pin)){
                    Toast.makeText(Page.this, "pin match: ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Page.this, "pin does not match: ", Toast.LENGTH_SHORT).show();
                    pin.getText().clear();
                }
            }
        });
    }
}
