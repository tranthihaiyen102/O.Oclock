package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ooclock.service.AlarmService;

public class TurnOffAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_off_alarm);
    }

    public void turnOffAlarm(View view) {
        Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
        getApplicationContext().stopService(intentService);
        finish();
//        Intent intent = new Intent(this, TurnOffAlarmMath.class);
//        startActivity(intent);
    }
}