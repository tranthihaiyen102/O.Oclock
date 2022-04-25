package com.example.ooclock;

import static com.example.ooclock.broadcastreceiver.AlarmBroadcastReceiver.MODE;
import static com.example.ooclock.broadcastreceiver.AlarmBroadcastReceiver.URI;
import static com.example.ooclock.broadcastreceiver.AlarmBroadcastReceiver.VIBRATE;
import static com.example.ooclock.broadcastreceiver.AlarmBroadcastReceiver.VOLUME;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;

public class SoundOptionActivity extends AppCompatActivity {
    Uri uri;
    int reqCode=123;
    SeekBar seekBar;
    CheckBox checkBox_vibrate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_option);
        seekBar = findViewById(R.id.seekBar);
        checkBox_vibrate = findViewById(R.id.checkBox_vibrate);
        Intent intent = getIntent();
        if(intent!=null){
            uri=Uri.parse(intent.getStringExtra(URI));
            seekBar.setProgress((int) (seekBar.getMax()*intent.getFloatExtra(VOLUME,1.0f)));
            checkBox_vibrate.setChecked(intent.getBooleanExtra(VIBRATE,false));
        }
    }

    public void back(View view) {
        finish();
    }

    public void chooseOK(View view) {
        Log.d("An_Test", ""+(float)seekBar.getProgress()/seekBar.getMax());
        Log.d("An_Test", ""+checkBox_vibrate.isChecked());
        Log.d("An_Test", uri.toString());
        Intent replyIntent = new Intent();
        replyIntent.putExtra(URI, uri.toString());
        replyIntent.putExtra(VOLUME, (float)seekBar.getProgress()/seekBar.getMax());
        replyIntent.putExtra(VIBRATE, checkBox_vibrate.isChecked());
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public void chooseSoundTrack(View view) {
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent,"Select Audio "),reqCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==reqCode) {
            uri = data.getData();
            Log.d("An_Test", uri.toString());
        }
    }
}