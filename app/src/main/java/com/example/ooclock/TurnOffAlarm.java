package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ooclock.service.AlarmService;
import com.example.ooclock.utils.TimePickerUtil;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

public class TurnOffAlarm extends AppCompatActivity {
    TextView txt_thucday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_off_alarm);
        txt_thucday = findViewById(R.id.txt_gioThucDay);
        Date currentTime = Calendar.getInstance().getTime();
        txt_thucday.setText(TimePickerUtil.tof12H(currentTime.getHours(),currentTime.getMinutes()));
    }

    public void turnOffAlarm(View view) {
        Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
        getApplicationContext().stopService(intentService);
        finish();
//        Intent intent = new Intent(this, TurnOffAlarmMath.class);
//        startActivity(intent);
    }
}