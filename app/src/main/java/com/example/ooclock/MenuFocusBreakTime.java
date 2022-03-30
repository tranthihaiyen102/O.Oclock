package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MenuFocusBreakTime extends AppCompatActivity {

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_focus_break_time);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);



        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);


        viewFlipper.setOutAnimation(out);
        viewFlipper.setInAnimation(in);



    }

    public void flipper(View view) {
        if (view.getId() == R.id.forward_flipper){
            viewFlipper.showNext();
        } else if (view.getId() == R.id.back_flipper) {
            viewFlipper.showPrevious();
        }
    }
}