package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MenuSettingsUX extends AppCompatActivity {

    RadioButton radioDarkTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_settings_ux);

        radioDarkTheme = findViewById(R.id.darktheme);
//        radioDarkTheme.setChecked(true);
    }

    public void back(View view) {
        finish();
    }
}