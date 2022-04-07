package com.example.ooclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuFocus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_focus);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.menu_focus);


        Button btn_setTimer = (Button) findViewById(R.id.btn_timer);


        btn_setTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuFocus.this, MenuFocusTiming.class));
            }
        });


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

                    case R.id.menu_stopwatch:
                        startActivity(new Intent(getApplicationContext(), MenuStopWatch.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_settings:
                        startActivity(new Intent(getApplicationContext(), MenuSettings.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_focus:
                        return true;
                }
                return false;
            }
        });



//        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MenuFocus.this);
//
//        myAlertBuilder.setMessage("Gà O.O sẽ theo dõi sự tập trung của bạn. Bạn đã sẵn sàng?");
//
//        myAlertBuilder.setPositiveButton("OK", (dialog, which) ->  {
//            startActivity(new Intent(this, MenuFocusGiveup.class));
//            overridePendingTransition(0,0);
//        });
//
//        myAlertBuilder.setNegativeButton("Hủy", (dialog, which) -> {
//           startActivity(new Intent(this, MenuFocusGiveup.class));
//           overridePendingTransition(0,0);
//        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btn_timer) {
            AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MenuFocus.this);

            myAlertBuilder.setMessage("Gà O.O sẽ theo dõi sự tập trung của bạn. Bạn đã sẵn sàng?");

            myAlertBuilder.setPositiveButton("OK", (dialog, which) ->  {
            startActivity(new Intent(this, MenuFocusGiveup.class));
            overridePendingTransition(0,0);
            });

        myAlertBuilder.setNegativeButton("Hủy", (dialog, which) -> {
           startActivity(new Intent(this, MenuFocusGiveup.class));
           overridePendingTransition(0,0);
        });
        }

        return super.onOptionsItemSelected(item);
    }





}
