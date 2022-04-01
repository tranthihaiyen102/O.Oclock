package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ooclock.data.AlarmModel;
import com.example.ooclock.model.Alarm;
import com.example.ooclock.utils.TimePickerUtil;

import java.sql.DatabaseMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAlarm extends AppCompatActivity {
    @BindView(R.id.textTimePicker) TextView textTimePicker;
    @BindView(R.id.editTextLabel)
    EditText title;
    @BindView(R.id.btn_schedule)
    Button btnScheduleAlarm;
    @BindView(R.id.checkBox)
    CheckBox recurring;
    @BindView(R.id.mon) CheckBox mon;
    @BindView(R.id.tue) CheckBox tue;
    @BindView(R.id.wed) CheckBox wed;
    @BindView(R.id.thu) CheckBox thu;
    @BindView(R.id.fri) CheckBox fri;
    @BindView(R.id.sat) CheckBox sat;
    @BindView(R.id.sun) CheckBox sun;
    @BindView(R.id.recurring_options)
    LinearLayout recurringOptions;
    private AlarmModel createAlarmViewModel;

//    private Spinner spinner_turnOffAlarm;

    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);
        ButterKnife.bind(this);
        createAlarmViewModel = ViewModelProviders.of(this).get(AlarmModel.class);
//        spinner_turnOffAlarm = findViewById(R.id.turnOffAlarm);

//        ArrayList<String> arrTurnOffAlarm = new ArrayList<String>();
//        arrTurnOffAlarm.add("Giải toán");
//        arrTurnOffAlarm.add("Chuông báo");
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrTurnOffAlarm);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_turnOffAlarm.setAdapter(arrayAdapter);
        CompoundButton.OnCheckedChangeListener weekdayListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkEveryDay())
                    recurring.setChecked(true);
                else
                    recurring.setChecked(false);
            }
        } ;
        recurring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mon.setChecked(true);
                    tue.setChecked(true);
                    wed.setChecked(true);
                    thu.setChecked(true);
                    fri.setChecked(true);
                    sat.setChecked(true);
                    sun.setChecked(true);
                }
                else {
                    if(checkEveryDay()) {
                        mon.setChecked(false);
                        tue.setChecked(false);
                        wed.setChecked(false);
                        thu.setChecked(false);
                        fri.setChecked(false);
                        sat.setChecked(false);
                        sun.setChecked(false);
                    }
                }
            }
        });
        mon.setOnCheckedChangeListener(weekdayListener);
        tue.setOnCheckedChangeListener(weekdayListener);
        wed.setOnCheckedChangeListener(weekdayListener);
        thu.setOnCheckedChangeListener(weekdayListener);
        fri.setOnCheckedChangeListener(weekdayListener);
        sat.setOnCheckedChangeListener(weekdayListener);
        sun.setOnCheckedChangeListener(weekdayListener);

        textTimePicker.setText(R.string.some_id);
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
                        },11, 0, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hour,minute);
                timePickerDialog.show();
            }
        });
        btnScheduleAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleAlarm();
                startActivity(new Intent(getBaseContext(),MainActivity.class));
            }
        });
    }

    private void scheduleAlarm() {
        int alarmId = new Random().nextInt(Integer.MAX_VALUE);

        Alarm alarm = new Alarm(
                alarmId,
                TimePickerUtil.getTimePickerHour(textTimePicker),
                TimePickerUtil.getTimePickerMinute(textTimePicker),
                title.getText().toString(),
                System.currentTimeMillis(),
                true,
                recurring.isChecked(),
                mon.isChecked(),
                tue.isChecked(),
                wed.isChecked(),
                thu.isChecked(),
                fri.isChecked(),
                sat.isChecked(),
                sun.isChecked()
        );

        createAlarmViewModel.insert(alarm);

        alarm.schedule(this);
    }

    public void previewAlarm(View view) {
        Intent intent = new Intent(this, TurnOffAlarm.class);
        startActivity(intent);
    }

    public boolean checkEveryDay(){
        return mon.isChecked()&tue.isChecked()&wed.isChecked()&thu.isChecked()&fri.isChecked()&sat.isChecked()&sun.isChecked();
    }
}