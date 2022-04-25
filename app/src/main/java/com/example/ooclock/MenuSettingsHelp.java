package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuSettingsHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_settings_help);
    }

    public void back(View view) {
        finish();
    }

    public void help1(View view) {
//        if (view.getId() == R.id.question1) {
//            Intent intent = new Intent(this,HelpQuestion1.class);
//            startActivity(intent);
//        }
        switch (view.getId()) {
            case R.id.question1:
                Intent intent1 = new Intent(this,HelpQuestion1.class);
                startActivity(intent1);
                break;
            case R.id.question2:
                Intent intent2 = new Intent(this,helpQuestion2.class);
                startActivity(intent2);
                break;
            case R.id.question3:
                Intent intent3 = new Intent(this,HelpQuestion3.class);
                startActivity(intent3);
                break;

        }
    }
}