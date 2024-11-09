package com.example.shambashop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.shambashop.ui.reports.reports;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, reports.newInstance())
                    .commitNow();
        }
    }
}