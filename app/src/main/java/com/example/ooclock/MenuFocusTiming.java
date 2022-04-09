package com.example.ooclock;

import static com.example.ooclock.broadcastreceiver.NotiBroadcastReceiver.NOTITIME;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

public class MenuFocusTiming extends AppCompatActivity {
    TextView txt_countdown;
    int millis;
    String format_time;
    String countdown_time;
    int time;
    CountDownTimer count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_focus_timing);

        Button btn_setStop = (Button) findViewById(R.id.btn_stop);

        btn_setStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MenuFocusTiming.this);

                myAlertBuilder.setMessage("Bạn có chắc chắn muốn dừng lại không?");

                myAlertBuilder.setPositiveButton("OK", (dialog, which) ->  {
                    count.cancel();
                    startActivity(new Intent(MenuFocusTiming.this, MenuFocusGiveup.class));
                    overridePendingTransition(0,0);
                    finish();
                });

                myAlertBuilder.setNegativeButton("Hủy", (dialog, which) -> {
                });
                AlertDialog alertDialog = myAlertBuilder.create();
                alertDialog.show();
            }
        });

        txt_countdown = findViewById(R.id.txt_countdown);
        countdown_time = getIntent().getStringExtra("countdown_time");
        time = Integer.parseInt(countdown_time.split(":")[0]);
        millis= time * 1000;
        count = new CountDownTimer(time * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                Log.d("An_Test",millis+"");
                format_time = String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millis) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                        TimeUnit.MILLISECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                txt_countdown.setText(format_time);
                millis-=1000;
            }
            public void onFinish() {
                try {
                    txt_countdown.setText(R.string.end_countdown);
                    Intent intent = new Intent(MenuFocusTiming.this,MenuFocusBreakTime.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex) {
                }
            }
        }.start();
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"You Are Not Allowed to Exit the App", Toast.LENGTH_SHORT).show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            Log.i("TEST", "Home Button");  // here you'll have to do something to prevent the button to go to the home screen
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}