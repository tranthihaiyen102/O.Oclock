package com.example.ooclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuSettings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_settings);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_UX);
        setSupportActionBar(toolbar);

////        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        if (getSupportActionBar() != null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }





        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.menu_settings);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_alarm:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.menu_clock:
                        startActivity(new Intent(getApplicationContext(), MenuClock.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.menu_stopwatch:
                        startActivity(new Intent(getApplicationContext(), MenuStopWatch.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.menu_focus:
                        startActivity(new Intent(getApplicationContext(), MenuFocus.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.menu_settings:
                        return true;
                }
                return false;
            }
        });
    }


    public void onClick(View view) {

//        Intent intent = new Intent(this, MenuSettingsUX.class);
//        overridePendingTransition(0,0);
//        startActivity(intent);

        if (view.getId() == R.id.option_UX){
            Intent intent = new Intent(this, MenuSettingsUX.class);
            overridePendingTransition(0,0);
            startActivity(intent);
        } else if (view.getId() == R.id.option_noti){
            Intent intent = new Intent(this, MenuSettingsNoti.class);
            overridePendingTransition(0,0);
            startActivity(intent);
        } else if (view.getId() == R.id.option_language){
            Intent intent = new Intent(this, MenuSettingsLang.class);
            overridePendingTransition(0,0);
            startActivity(intent);
        } else if (view.getId() == R.id.option_infor){
            Intent intent = new Intent(this, MenuSettingsInfo.class);
            overridePendingTransition(0,0);
            startActivity(intent);
        } else if (view.getId() == R.id.option_help){
            Intent intent = new Intent(this, MenuSettingsHelp.class);
            overridePendingTransition(0,0);
            startActivity(intent);
        }
    }



//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (item.getItemId() == R.id.menu_settings){
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}