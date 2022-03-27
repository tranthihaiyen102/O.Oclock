package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.DatabaseMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateAlarm extends AppCompatActivity {

//    private Spinner spinner_turnOffAlarm;
    TextView textTimePicker;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

//        spinner_turnOffAlarm = findViewById(R.id.turnOffAlarm);

        textTimePicker = findViewById(R.id.textTimePicker);

//        ArrayList<String> arrTurnOffAlarm = new ArrayList<String>();
//        arrTurnOffAlarm.add("Giải toán");
//        arrTurnOffAlarm.add("Chuông báo");
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrTurnOffAlarm);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_turnOffAlarm.setAdapter(arrayAdapter);

        textTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CreateAlarm.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int m) {
                                hour = hourOfDay;
                                minute = m;
                                String time = hour + ":" + minute;
                                //24 hours format
                                SimpleDateFormat f24H = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24H.parse(time);
                                    //12 hours format
                                    SimpleDateFormat f12H = new SimpleDateFormat("hh:mm aa");
                                    textTimePicker.setText(f12H.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12, 0, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hour,minute);
                timePickerDialog.show();
            }
        });
    }
}