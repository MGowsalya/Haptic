package com.example.admin.haptic;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
EditText email,mobile,pin;
    SQLiteDatabase db;
    int status = 1,st;
    String user,pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = getApplicationContext().openOrCreateDatabase("Master.db", Context.MODE_PRIVATE ,null);
        db.execSQL("create table if not exists LoginDetails(Username varchar,Moblienumber int,Pin int,Status int);");
            setContentView(R.layout.activity_login);
            email = findViewById(R.id.username);
            mobile = findViewById(R.id.phone);
            pin = findViewById(R.id.pin);

            InputFilter filter = new InputFilter() {
                public CharSequence filter(CharSequence source, int start, int end,
                                           Spanned dest, int dstart, int dend) {
                    for (int i = start; i < end; i++) {
                        if (Character.isSpaceChar(source.charAt(i))) {
                            return "";
                        }
                    }
                    return null;
                }
            };
            email.setFilters(new InputFilter[]{filter});
            email.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Validation.isEmailAddress(email, true);
                }
            });

            findViewById(R.id.log_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String mo = mobile.getText().toString();
                    String pi = pin.getText().toString();
                    if (checkValidation()) {
                        if (mo.length() == 10 && pi.length() == 4) {
                            ContentValues values = new ContentValues();
                            values.put("Username", email.getText().toString());
                            values.put("Moblienumber", mobile.getText().toString());
                            values.put("Pin", pin.getText().toString());
                            values.put("Status", status);
                            db.insert("LoginDetails", null, values);
                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this,Page.class);
                            startActivity(intent);
                        } else {
                            if (mo.length() != 10) {
                                mobile.requestFocus();
                            } else if (pin.length() != 4) {
                                pin.requestFocus();
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Email id is invalid", Toast.LENGTH_SHORT).show();
                    }

                }

            });

    }
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.isEmailAddress(email, true)) ret = false;
        return ret;
    }
}
