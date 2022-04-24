package com.example.ooclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.FocusFinder;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Date;

public class MenuClock extends AppCompatActivity {
    TextView txtHours;
    TextView txtDays;
    Handler handler;
    Runnable clockRunable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_clock);

        txtHours = findViewById(R.id.txt_hours);
        txtDays = findViewById(R.id.txt_days);
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
        reTime();
    }

    private void reTime()
    {
        // Get the text view.
        // Creates a new Handler
        handler
                = new Handler();
        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
        handler.post(clockRunable = new Runnable() {
            @Override

            public void run()
            {
                Log.d("An_Test","It's show time");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                int h=calendar.getTime().getHours();
                int m=calendar.getTime().getMinutes();
                String dw=getDayofWeek(calendar);
                int dm=calendar.get(Calendar.DAY_OF_MONTH);
                int mo=calendar.get(Calendar.MONTH)+1;
                int y=calendar.get(Calendar.YEAR);
                txtHours.setText(String.format("%02d:%02d",h,m));
                txtDays.setText(String.format("%s, %02d/%02d/%04d",dw,dm,mo,y));
                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(clockRunable, 1000);
            }
        });
    }

    public String getDayofWeek(Calendar calendar){
        String dom="";
        if(calendar.get(Calendar.DAY_OF_WEEK)==1)
            dom="CN";
        else
            dom="Thứ "+calendar.get(Calendar.DAY_OF_WEEK);
        return dom;
    }

    @Override
    protected void onStop() {
        handler.removeCallbacks(clockRunable);
        super.onStop();
    }
}