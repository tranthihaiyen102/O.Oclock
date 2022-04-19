package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startActivity(new Intent(StartScreen.this, MainActivity.class));
        finish();
        return super.onTouchEvent(event);
    }
}