package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.concurrent.TimeUnit;

public class MenuFocusBreakTime extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    Button btn_break;
    View back_flipper;
    View forward_flipper;
    int millis;
    String format_time;
    TextView txt_countdown;
    String countdown_time;
    int time;
    CountDownTimer count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_focus_break_time);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);



        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);


        viewFlipper.setOutAnimation(out);
        viewFlipper.setInAnimation(in);
        btn_break = findViewById(R.id.btn_break);
        back_flipper = findViewById(R.id.back_flipper);
        forward_flipper = findViewById(R.id.forward_flipper);
        btn_break.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_flipper.setVisibility(View.INVISIBLE);
                forward_flipper.setVisibility(View.INVISIBLE);
                btn_break.setText("Bắt đầu luôn");
                btn_break.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count.cancel();
                        Intent intent = new Intent(MenuFocusBreakTime.this,MenuFocus.class);
                        startActivity(intent);
                        finish();
                    }
                });
                txt_countdown = (TextView) viewFlipper.getCurrentView();
                countdown_time = txt_countdown.getText().toString();
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
                            Intent intent = new Intent(MenuFocusBreakTime.this,MenuFocus.class);
                            startActivity(intent);
                            finish();
                        } catch (Exception ex) {
                        }
                    }
                }.start();
            }
        });
    }

    public void flipper(View view) {
        if (view.getId() == R.id.forward_flipper){
            viewFlipper.showNext();
        } else if (view.getId() == R.id.back_flipper) {
            viewFlipper.showPrevious();
        }
    }

    @Override
    public void onBackPressed(){
        count.cancel();
        super.onBackPressed();
    }
}