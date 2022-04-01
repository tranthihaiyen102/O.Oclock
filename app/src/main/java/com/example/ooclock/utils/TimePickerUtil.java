package com.example.ooclock.utils;

import android.os.Build;
import android.widget.TextView;
import android.widget.TimePicker;

public final class TimePickerUtil {
    public static int getTimePickerHour(TextView tp) {
        String time = tp.getText().toString();
        int hour = Integer.parseInt(time.split(":")[0]);
        String am = time.split(" ")[1];
        if(am.equals("PM")){
            hour+=12;
        }
        if(hour>=24) hour-=24;
        return hour;
    }

    public static int getTimePickerMinute(TextView tp) {
        String time = tp.getText().toString();
        int minute = Integer.parseInt(time.split(":")[1].split(" ")[0]);
        return minute;
    }
}
