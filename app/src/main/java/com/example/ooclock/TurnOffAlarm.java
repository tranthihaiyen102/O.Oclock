package com.example.ooclock;

import static com.example.ooclock.broadcastreceiver.AlarmBroadcastReceiver.MODE;
import static com.example.ooclock.broadcastreceiver.AlarmBroadcastReceiver.TITLE;

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
    TextView noi_dung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_off_alarm);
        txt_thucday = findViewById(R.id.txt_gioThucDay);
        Date currentTime = Calendar.getInstance().getTime();
        txt_thucday.setText(TimePickerUtil.tof12H(currentTime.getHours(),currentTime.getMinutes()));
        noi_dung = findViewById(R.id.noi_dung);
        noi_dung.setText(getIntent().getStringExtra(TITLE));
    }

    public void turnOffAlarm(View view) {
        if(getIntent().getStringExtra(MODE)!=null) {
            if (getIntent().getStringExtra(MODE).startsWith("1")) {
                Intent intent = new Intent(this, TurnOffAlarmMath.class);
                intent.putExtra(MODE, getIntent().getStringExtra(MODE));
                startActivity(intent);
                finish();
            } else turnOff();
        }
        else turnOff();
    }
    public void turnOff(){
        Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
        getApplicationContext().stopService(intentService);
        finish();
    }
}