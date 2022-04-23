package com.example.ooclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.preference.PreferenceManager;
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
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "myPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_stop_watch);
        runed=false;
        btn_rr = findViewById(R.id.btn_reset);
        btn_ss = findViewById(R.id.button2);
        txt_time = findViewById(R.id.textView);
        record_view = findViewById(R.id.view3);
        record_view.setMovementMethod(new ScrollingMovementMethod());
        id=1;
        prerecord=0;
        minus_time_start=0;

//        if (savedInstanceState != null) {
//            milisecs
//                    = savedInstanceState
//                    .getLong("seconds");
//            running
//                    = savedInstanceState
//                    .getBoolean("running");
//            wasRunning
//                    = savedInstanceState
//                    .getBoolean("wasRunning");
//            start=savedInstanceState
//                    .getLong("start", 0L);
//            minus_time_start=savedInstanceState
//                    .getLong("minus_time_start", 0L);
//            runed=savedInstanceState
//                    .getBoolean("runed", false);
//            record=savedInstanceState
//                    .getLong("record", 0L);
//            prerecord=savedInstanceState
//                    .getLong("prerecord", 0L);
//            id=savedInstanceState
//                    .getInt("id", 0);
//            record_view.setText(savedInstanceState.getString("record_view_string"));
//            txt_time.setText(savedInstanceState.getString("txt_time_string"));
//            if(running){
//                btn_rr.setText(R.string.btn_reset);
//                btn_ss.setText(R.string.btn_start);
//            }
//            else {
//                btn_rr.setText(R.string.record);
//                btn_ss.setText(R.string.stop);
//            }
//        }

        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        milisecs
                = mPreferences
                .getLong("seconds",0L);
        running
                = mPreferences
                .getBoolean("running",false);
        wasRunning
                = mPreferences
                .getBoolean("wasRunning",false);
        start=mPreferences
                .getLong("start", 0L);
        minus_time_start=mPreferences
                .getLong("minus_time_start", 0L);
        runed=mPreferences
                .getBoolean("runed", false);
        record=mPreferences
                .getLong("record", 0L);
        prerecord=mPreferences
                .getLong("prerecord", 0L);
        id=mPreferences
                .getInt("id", 0);
        record_view.setText(mPreferences.getString("record_view_string",""));
        txt_time.setText(mPreferences.getString("txt_time_string","00:00:00.00"));
        if(!runed){
            btn_rr.setText(R.string.btn_reset);
            btn_ss.setText(R.string.btn_start);
        }
        else {
            btn_rr.setText(R.string.record);
            btn_ss.setText(R.string.stop);
        }

        runTimer();
        navigation();
    }


//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState)
//    {
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState
//                .putLong("milisecs", milisecs);
//        savedInstanceState
//                .putBoolean("running", running);
//        savedInstanceState
//                .putBoolean("wasRunning", wasRunning);
//        savedInstanceState
//                .putLong("start", start);
//        savedInstanceState
//                .putLong("minus_time_start", minus_time_start);
//        savedInstanceState
//                .putBoolean("runed", runed);
//        savedInstanceState
//                .putLong("record", record);
//        savedInstanceState
//                .putLong("prerecord", prerecord);
//        savedInstanceState
//                .putInt("id", id);
//        savedInstanceState.putString("record_view_string",record_view.getText().toString());
//        savedInstanceState.putString("txt_time_string",txt_time.getText().toString());
//    }

    // If the activity is paused,
    // stop the stopwatch.
    @Override
    protected void onPause()
    {
        super.onPause();
        wasRunning = running;
        running = false;
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor
                .putLong("milisecs", milisecs);
        preferencesEditor
                .putBoolean("running", running);
        preferencesEditor
                .putBoolean("wasRunning", wasRunning);
        preferencesEditor
                .putLong("start", start);
        preferencesEditor
                .putLong("minus_time_start", minus_time_start);
        preferencesEditor
                .putBoolean("runed", runed);
        preferencesEditor
                .putLong("record", record);
        preferencesEditor
                .putLong("prerecord", prerecord);
        preferencesEditor
                .putInt("id", id);
        preferencesEditor.putString("record_view_string",record_view.getText().toString());
        preferencesEditor.putString("txt_time_string",txt_time.getText().toString());
        preferencesEditor.commit();
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
        if(runed){
            minus_time_start=System.currentTimeMillis();
            runed=false;
            btn_rr.setText(R.string.btn_reset);
            btn_ss.setText(R.string.btn_start);
        }
        else {
            start +=System.currentTimeMillis()-minus_time_start;
            runed=true;
            btn_rr.setText(R.string.record);
            btn_ss.setText(R.string.stop);
        }
    }


    public void onClickReset_Record(View view)
    {
        if(runed){
            record = milisecs;
            String recordText=String.format("%-5s          %12s          %12s","#"+id,format_time(record,false),format_time(record-prerecord,false));
            record_view.setText("\n"+recordText+record_view.getText());
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
                String time = format_time(milisecs,true);
                if (runed) {
                    txt_time.setText(time);
                    milisecs=System.currentTimeMillis()-start;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1);
            }
        });
    }

    public String format_time(long mils,boolean zero){
        String time;
        int hours = (int) (mils/1000) / 3600;
        int minutes = (int) ((mils/1000) % 3600) / 60;
        int secs = (int) (mils/1000) % 60;
        int mili = (int) (mils % 1000)/10;

        // Format the seconds into hours, minutes,
        // and seconds.
        if(zero) {
            time = String.format(Locale.getDefault(),
                    "%02d:%02d:%02d.%02d", hours,
                    minutes, secs, mili);
        }
        else {
            if(hours==0){
                if(minutes==0){
                    time = String.format(Locale.getDefault(),
                            "%02d.%02d", secs, mili);
                }
                else{
                    time = String.format(Locale.getDefault(),
                            "%02d:%02d.%02d",minutes, secs, mili);
                }
            }
            else{
                time = String.format(Locale.getDefault(),
                        "%02d:%02d:%02d.%02d", hours,
                        minutes, secs, mili);
            }
        }
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
                        finish();
                        return true;

                    case R.id.menu_clock:
                        startActivity(new Intent(getApplicationContext(), MenuClock.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.menu_focus:
                        startActivity(new Intent(getApplicationContext(), MenuFocus.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.menu_settings:
                        startActivity(new Intent(getApplicationContext(), MenuSettings.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.menu_stopwatch:
                        return true;
                }
                return false;
            }
        });
    }
}
