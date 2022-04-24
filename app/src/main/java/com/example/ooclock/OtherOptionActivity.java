package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class OtherOptionActivity extends AppCompatActivity {

    CheckBox reminderCheckBox;
    EditText text_reminder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_option);

        reminderCheckBox = findViewById(R.id.reminder);
        text_reminder = findViewById(R.id.text_reminder);
        text_reminder.setVisibility(View.INVISIBLE);
    }

    public void back(View view) {
        finish();
    }

    public void reminderCheck(View view) {
        if (reminderCheckBox.isChecked()){
            text_reminder.setVisibility(View.VISIBLE);
        }else {
            text_reminder.setVisibility(View.INVISIBLE);
        }
    }
}