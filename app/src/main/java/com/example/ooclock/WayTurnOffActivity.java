package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class WayTurnOffActivity extends AppCompatActivity {

    ConstraintLayout math_option_layout;
    RadioButton radio_bell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_turn_off);

        math_option_layout = findViewById(R.id.math_option);
        math_option_layout.setVisibility(View.INVISIBLE);
        radio_bell = findViewById(R.id.radio_bell);
        radio_bell.setChecked(true);
    }

    public void optionCheck(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_bell:
                if (checked){
                    math_option_layout.setVisibility(View.INVISIBLE);
                }
            case R.id.radio_math:
                if (checked){
                    math_option_layout.setVisibility(View.VISIBLE);
                }
        }
    }

    public void back(View view) {
        finish();
    }
}