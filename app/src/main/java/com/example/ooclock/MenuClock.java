package com.example.ooclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.FocusFinder;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuClock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_clock);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.menu_clock);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_alarm:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.menu_focus:
                        startActivity(new Intent(getApplicationContext(), MenuFocus.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.menu_stopwatch:
                        startActivity(new Intent(getApplicationContext(), MenuStopWatch.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.menu_settings:
                        startActivity(new Intent(getApplicationContext(), MenuSettings.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.menu_clock:
                        return true;
                }
                return false;
            }
        });
    }
}