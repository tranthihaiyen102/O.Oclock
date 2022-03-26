package com.example.ooclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuStopWatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_stop_watch);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        bottomNavigationView.setSelectedItemId(R.id.menu_stopwatch);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()){
                   case R.id.menu_alarm:
                       startActivity(new Intent(getApplicationContext(), MainActivity.class));
                       overridePendingTransition(0,0);
                       return true;

                   case R.id.menu_clock:
                       startActivity(new Intent(getApplicationContext(), MenuClock.class));
                       overridePendingTransition(0,0);
                       return true;

                   case R.id.menu_focus:
                       startActivity(new Intent(getApplicationContext(), MenuFocus.class));
                       overridePendingTransition(0,0);
                       return true;

                   case R.id.menu_settings:
                       startActivity(new Intent(getApplicationContext(), MenuSettings.class));
                       overridePendingTransition(0,0);
                       return true;

                   case R.id.menu_stopwatch:
                       return true;
               }
                return false;
            }
        });
    }
}