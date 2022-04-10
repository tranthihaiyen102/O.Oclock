package com.example.ooclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuFocus extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_focus);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.menu_focus);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);



        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);


        viewFlipper.setOutAnimation(out);
        viewFlipper.setInAnimation(in);


        Button btn_setTimer = (Button) findViewById(R.id.btn_timer);


        btn_setTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MenuFocus.this);

                myAlertBuilder.setMessage("Gà O.O sẽ theo dõi sự tập trung của bạn. Bạn đã sẵn sàng?");

                myAlertBuilder.setPositiveButton("OK", (dialog, which) ->  {
                    String countdown_time = ((TextView)viewFlipper.getCurrentView()).getText().toString();
                    startActivity(new Intent(MenuFocus.this, MenuFocusTiming.class).putExtra("countdown_time",countdown_time));
                    overridePendingTransition(0,0);
                    finish();
                });

                myAlertBuilder.setNegativeButton("Hủy", (dialog, which) -> {
                });
                AlertDialog alertDialog = myAlertBuilder.create();
                alertDialog.show();
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_alarm:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_clock:
                        startActivity(new Intent(getApplicationContext(), MenuClock.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_stopwatch:
                        startActivity(new Intent(getApplicationContext(), MenuStopWatch.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_settings:
                        startActivity(new Intent(getApplicationContext(), MenuSettings.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_focus:
                        return true;
                }
                return false;
            }
        });



//        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MenuFocus.this);
//
//        myAlertBuilder.setMessage("Gà O.O sẽ theo dõi sự tập trung của bạn. Bạn đã sẵn sàng?");
//
//        myAlertBuilder.setPositiveButton("OK", (dialog, which) ->  {
//            startActivity(new Intent(this, MenuFocusGiveup.class));
//            overridePendingTransition(0,0);
//        });
//
//        myAlertBuilder.setNegativeButton("Hủy", (dialog, which) -> {
//           startActivity(new Intent(this, MenuFocusGiveup.class));
//           overridePendingTransition(0,0);
//        });
//        AlertDialog alertDialog = myAlertBuilder.create();
//        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btn_timer) {
            AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MenuFocus.this);

            myAlertBuilder.setMessage("Gà O.O sẽ theo dõi sự tập trung của bạn. Bạn đã sẵn sàng?");

            myAlertBuilder.setPositiveButton("OK", (dialog, which) -> {
                startActivity(new Intent(this, MenuFocusGiveup.class));
                overridePendingTransition(0, 0);
            });

            myAlertBuilder.setNegativeButton("Hủy", (dialog, which) -> {
                startActivity(new Intent(this, MenuFocusGiveup.class));
                overridePendingTransition(0, 0);
            });
            AlertDialog alertDialog = myAlertBuilder.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void flipper(View view) {
        if (view.getId() == R.id.forward_flipper){
            viewFlipper.showNext();
        } else if (view.getId() == R.id.back_flipper) {
            viewFlipper.showPrevious();
        }
    }
}
