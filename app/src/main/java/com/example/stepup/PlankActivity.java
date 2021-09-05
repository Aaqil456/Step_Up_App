package com.example.stepup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PlankActivity extends AppCompatActivity {
//Initialize Variable
    Button btnStart,btnStop,btnReset;
    Chronometer mChronometer;
    private long lastPause;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plank);
        //assign variable

        btnStart=findViewById(R.id.buttonStart);
        btnStop=findViewById(R.id.buttonStop);
        btnReset=findViewById(R.id.buttonReset);
        mChronometer = (Chronometer) findViewById(R.id.chronometer);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastPause != 0){
                    mChronometer.setBase(mChronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                }
                else{
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }

                mChronometer.start();
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);

            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPause = SystemClock.elapsedRealtime();
                mChronometer.stop();
                btnStop.setEnabled(false);
                btnStart.setEnabled(true);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChronometer.stop();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                lastPause = 0;
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
            }
        });



    }
}