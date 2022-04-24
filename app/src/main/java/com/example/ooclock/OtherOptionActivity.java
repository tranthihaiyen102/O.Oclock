package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class OtherOptionActivity extends AppCompatActivity {

    CheckBox reminderCheckBox;
    CheckBox snoozeCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_option);
        reminderCheckBox = findViewById(R.id.reminder);
        snoozeCheckBox = findViewById(R.id.snooze);
    }

    public void back(View view) {
        finish();
    }
}