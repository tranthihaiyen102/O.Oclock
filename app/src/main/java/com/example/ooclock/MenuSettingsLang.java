package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MenuSettingsLang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_settings_lang);
    }

    public void langCheck(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.tiengviet:
                if (checked);
                break;
            case R.id.tienganh:
                if (checked);
                break;

            default:
                break;
        }
    }

    public void back(View view) {
        finish();
    }
}