package com.example.ooclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
//import android.widget.Toolbar;


import com.example.ooclock.alarmlist.AlarmRecyclerViewAdapter;
import com.example.ooclock.data.AlarmModel;
import com.example.ooclock.listeners.OnToggleAlarmListener;
import com.example.ooclock.model.Alarm;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnToggleAlarmListener {
    private AlarmRecyclerViewAdapter alarmRecyclerViewAdapter;
    private AlarmModel alarmsListViewModel;
    private RecyclerView alarmsRecyclerView;
    TextView txtMessage;

    Handler handler;
    Runnable clockRunable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMessage = findViewById(R.id.txt_message);
        alarmRecyclerViewAdapter = new AlarmRecyclerViewAdapter(this);
        alarmsListViewModel = ViewModelProviders.of(this).get(AlarmModel.class);
        alarmsListViewModel.getAlarmsLiveData().observe(this, new Observer<List<Alarm>>() {
            @Override
            public void onChanged(List<Alarm> alarms) {
                if (alarms != null) {
                    Collections.sort(alarms);
                    alarmRecyclerViewAdapter.setAlarms(alarms);
                    txtMessage.setText(alarmRecyclerViewAdapter.getWillRing());
                }
            }
        });


        alarmsRecyclerView = findViewById(R.id.fragment_listalarms_recylerView);
        alarmsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        alarmsRecyclerView.setAdapter(alarmRecyclerViewAdapter);

//        SwitchCompat switchCompat = findViewById(R.id.switch1);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.menu_alarm);


//        switchCompat.setOnCheckedChangeListener(
//                new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                        if (switchCompat.isChecked()){
//
//                        }
//                    }
//                }
//        );


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_clock:
                        startActivity(new Intent(getApplicationContext(), MenuClock.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.menu_focus:
                        startActivity(new Intent(getApplicationContext(), MenuFocus.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.menu_stopwatch:
                        startActivity(new Intent(getApplicationContext(), MenuStopWatch.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.menu_settings:
                        startActivity(new Intent(getApplicationContext(), MenuSettings.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.menu_alarm:
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
                txtMessage.setText(alarmRecyclerViewAdapter.getWillRing());
                handler.postDelayed(clockRunable, 1000);
            }
        });
    }

    public void addNewAlarm(View view) {
        Intent intent = new Intent(this, CreateAlarm.class);
        startActivity(intent);
    }

    @Override
    public void onToggle(Alarm alarm) {
        if (alarm.isStarted()) {
            alarm.cancelAlarm(this);
            alarmsListViewModel.update(alarm);
        } else {
            alarm.schedule(this);
            alarmsListViewModel.update(alarm);
        }
    }

    @Override
    protected void onStop() {
        handler.removeCallbacks(clockRunable);
        super.onStop();
    }

    public void onHoldDelete(Alarm alarm) {
        alarmsListViewModel.deleteAlarm(alarm);
    }
}