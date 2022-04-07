package com.example.ooclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ooclock.service.AlarmService;

import java.util.concurrent.ThreadLocalRandom;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TurnOffAlarmMath extends AppCompatActivity {
    @BindView(R.id.txtPhepToan) TextView txtPhepToan;
    @BindView(R.id.txt_DauBang) TextView txt_DauBang;
    @BindView(R.id.quiz_page) TextView quiz_page;
    @BindView(R.id.computed_time) TextView computed_time;
    @BindView(R.id.btn_delNum) Button btn_delNum;
    @BindView(R.id.btn_OK) Button btn_OK;
    @BindView(R.id.button_1) Button button_1;
    @BindView(R.id.button_2) Button button_2;
    @BindView(R.id.button_3) Button button_3;
    @BindView(R.id.button_4) Button button_4;
    @BindView(R.id.button_5) Button button_5;
    @BindView(R.id.button_6) Button button_6;
    @BindView(R.id.button_7) Button button_7;
    @BindView(R.id.button_8) Button button_8;
    @BindView(R.id.button_9) Button button_9;
    @BindView(R.id.button_0) Button button_0;
    int result;
    View.OnClickListener numberOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            txt_DauBang.setText(txt_DauBang.getText().toString()+((Button)view).getText());
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_off_alarm_math);
        ButterKnife.bind(this);
        button_1.setOnClickListener(numberOnClick);
        button_2.setOnClickListener(numberOnClick);
        button_3.setOnClickListener(numberOnClick);
        button_4.setOnClickListener(numberOnClick);
        button_5.setOnClickListener(numberOnClick);
        button_6.setOnClickListener(numberOnClick);
        button_7.setOnClickListener(numberOnClick);
        button_8.setOnClickListener(numberOnClick);
        button_9.setOnClickListener(numberOnClick);
        button_0.setOnClickListener(numberOnClick);
        btn_delNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kq;
                kq = txt_DauBang.getText().toString();
                if(kq.length()>2){
                    txt_DauBang.setText(kq.substring(0,kq.length()-1));
                }
            }
        });
        result = toan_1();
        Log.d("An_Test",result+"");
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kq;
                String kq_str = txt_DauBang.getText().toString().substring(2);
                Log.d("An_Test",kq_str);
                if(kq_str.length()>2) kq = Integer.parseInt(kq_str);
                else kq=0;
                if(kq==result) {
                    Toast.makeText(getBaseContext(), "Correct answer", Toast.LENGTH_LONG).show();
                    Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
                    getApplicationContext().stopService(intentService);
                    finish();
                }
                else
                    Toast.makeText(getBaseContext(),"Incorrect answer",Toast.LENGTH_LONG).show();
            }
        });
    }

    public int toan_1(){
        int so1, so2;
        so1= ThreadLocalRandom.current().nextInt(1, 999999999 + 1);
        so2= ThreadLocalRandom.current().nextInt(1, 999999999 + 1);
        txtPhepToan.setText(so1+" + "+so2);
        return (so1+so2);
    }
}