package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class CreateAlarm extends AppCompatActivity {

    private Spinner spinner_turnOffAlarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        spinner_turnOffAlarm = findViewById(R.id.turnOffAlarm);

        ArrayList<String> arrTurnOffAlarm = new ArrayList<String>();
        arrTurnOffAlarm.add("Giải toán");
        arrTurnOffAlarm.add("Chuông báo");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrTurnOffAlarm);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_turnOffAlarm.setAdapter(arrayAdapter);






    }
}