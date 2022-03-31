package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
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

    TextView textTimePicker;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        textTimePicker = findViewById(R.id.textTimePicker);

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

    public void previewAlarm(View view) {
        Intent intent = new Intent(this, TurnOffAlarm.class);
        startActivity(intent);
    }

    public void chooseWay(View view) {
        Intent intent = new Intent(this, WayTurnOffActivity.class);
        startActivity(intent);
    }

    public void soundOption(View view) {
        Intent intent = new Intent(this, SoundOptionActivity.class);
        startActivity(intent);
    }

    public void chooseOtherOption(View view) {
        Intent intent = new Intent(this, OtherOptionActivity.class);
        startActivity(intent);
    }
}