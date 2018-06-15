package com.example.admin.haptic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayShowHomeEnabled(true);
//        actionbar.setDisplayUseLogoEnabled(true);
//        actionbar.setLogo(R.mipmap.ogicon);
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,Login.class);
                startActivity(in);
                finish();
            }
        });
    }
}
