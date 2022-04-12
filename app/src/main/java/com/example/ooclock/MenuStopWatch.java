package com.example.ooclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class MenuStopWatch extends AppCompatActivity {
    private long milisecs = 0;
    long start;
    long minus_time_start;
    boolean runed;
    private boolean running;
    private boolean wasRunning;
    long record;
    long prerecord;
    int id;
    Button btn_ss;
    Button btn_rr;
    TextView txt_time;
    TextView record_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_stop_watch);
        runed=false;
        if (savedInstanceState != null) {
            milisecs
                    = savedInstanceState
                    .getLong("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }
        btn_rr = findViewById(R.id.btn_reset);
        btn_ss = findViewById(R.id.button2);
        txt_time = findViewById(R.id.textView);
        record_view = findViewById(R.id.view3);
        record_view.setMovementMethod(new ScrollingMovementMethod());
        id=1;
        prerecord=0;
        minus_time_start=0;

        runTimer();
        navigation();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState
                .putLong("milisecs", milisecs);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }

    // If the activity is paused,
    // stop the stopwatch.
    @Override
    protected void onPause()
    {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    // If the activity is resumed,
    // start the stopwatch
    // again if it was running previously.
    @Override
    protected void onResume()
    {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }


    public void onClickStart_Stop(View view)
    {
        if(running){
            minus_time_start=System.currentTimeMillis();
            running=false;
            btn_rr.setText(R.string.btn_reset);
            btn_ss.setText(R.string.btn_start);
        }
        else {
            start +=System.currentTimeMillis()-minus_time_start;
            running=true;
            btn_rr.setText(R.string.record);
            btn_ss.setText(R.string.stop);
        }
    }


    public void onClickReset_Record(View view)
    {
        if(running){
            record = milisecs;
            record_view.setText("\n"+"     #"+id_toString(id)+format_time(record)+"       "+format_time(record-prerecord)+record_view.getText());
            id++;
            prerecord = milisecs;
        }
        else {
            milisecs=0;
            start=0;
            minus_time_start=0;
            txt_time.setText(R.string.txt_timer);

            id=1;
            prerecord=0;
            record_view.setText("");
        }
    }

    public String id_toString(int id){
        String id_str;
        if(id<10) id_str=id+"        ";
        else if(id<100) id_str=id+"      ";
        else if(id<1000) id_str=id+"    ";
        else if(id<10000) id_str=id+"  ";
        else id_str=id+"";
        return id_str;
    }


    private void runTimer()
    {
        // Get the text view.
        // Creates a new Handler
        final Handler handler
                = new Handler();
        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
        handler.post(new Runnable() {
            @Override

            public void run()
            {
                String time = format_time(milisecs);
                if (running) {
                    txt_time.setText(time);
                    milisecs=System.currentTimeMillis()-start;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1);
            }
        });
    }

    public String format_time(long mils){
        int hours = (int) (mils/1000) / 3600;
        int minutes = (int) ((mils/1000) % 3600) / 60;
        int secs = (int) (mils/1000) % 60;
        int mili = (int) (mils % 1000)/10;

        // Format the seconds into hours, minutes,
        // and seconds.
        String time = String.format(Locale.getDefault(),
                "%02d:%02d:%02d.%02d", hours,
                minutes, secs,mili);
        return time;
    }

    public void navigation(){
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