package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TurnOffAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_off_alarm);
    }

    public void turnOffAlarm(View view) {
        Intent intent = new Intent(this, TurnOffAlarmMath.class);
        startActivity(intent);
    }
}