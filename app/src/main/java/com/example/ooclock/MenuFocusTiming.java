package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuFocusTiming extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_focus_timing);

        Button btn_setStop = (Button) findViewById(R.id.btn_stop);

        btn_setStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuFocusTiming.this, MenuFocusGiveup.class));
                overridePendingTransition(0,0);
            }
        });

    }
}