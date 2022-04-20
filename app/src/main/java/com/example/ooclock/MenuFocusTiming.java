package com.example.ooclock;

import static com.example.ooclock.broadcastreceiver.NotiBroadcastReceiver.NOTITIME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
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
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    TextView txt_countdown;
    int millis;
    String format_time;
    String countdown_time;
    int time;
    CountDownTimer count;
    WindowInsetsControllerCompat windowInsetsController;
    boolean touch;
    boolean finish;
    int reset_touch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_focus_timing);
        Log.d("An_Test","timing");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        windowInsetsController = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        if (windowInsetsController == null) {
            return;
        }
        // Configure the behavior of the hidden system bars
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
        windowInsetsController.addOnControllableInsetsChangedListener(new WindowInsetsControllerCompat.OnControllableInsetsChangedListener() {
            @Override
            public void onControllableInsetsChanged(@NonNull WindowInsetsControllerCompat controller, int typeMask) {
                Toast.makeText(MenuFocusTiming.this,"Xin đừng chạm vào màn hình",Toast.LENGTH_LONG).show();
            }
        });
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

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
        reset_touch=0;
        finish=false;
        count = new CountDownTimer(time * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                reset_touch++;
                if(reset_touch%2==1){
                    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
                    touch=false;
                }
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
                    mediaPlayer = MediaPlayer.create(MenuFocusTiming.this, R.raw.finish_sound);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(false);
                    mediaPlayer.start();
                    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    long[] pattern = {0, 100, 1000};
                    vibrator.vibrate(pattern, -1);
                    finish=true;
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MenuFocusTiming.this);

        myAlertBuilder.setMessage("Đang trong lúc tập trung bạn chạm vào màn hình điện thoại làm gì vậy? Nó sẽ ảnh hưởng đến sự tập trung của bạn đó! " +
                "\nBạn có chắc muốn tiếp tục hành động này không :(");

        myAlertBuilder.setPositiveButton("OK", (dialog, which) ->  {
            windowInsetsController.show(WindowInsetsCompat.Type.systemBars());
        });

        myAlertBuilder.setNegativeButton("Hủy", (dialog, which) -> {
            touch=false;
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        });
        AlertDialog alertDialog = myAlertBuilder.create();
        if(!touch){
            alertDialog.show();
            touch=true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onUserLeaveHint() {
        if(!finish){
            Log.d("An_Test","LEAVE");
        }
        super.onUserLeaveHint();
    }
}