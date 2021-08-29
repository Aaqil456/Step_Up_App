package com.example.stepup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StepCounter extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mStepCount;
    private TextView tv_steps;
    private Boolean walk = false;
    private Button resetBtn;
    int stepNum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        //not sure
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //finish here


        //reset button
        resetBtn = findViewById(R.id.btnReset);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_steps.setText("0");
                Toast.makeText(StepCounter.this, "0", Toast.LENGTH_SHORT).show();

            }
        });
        //until here


        tv_steps=findViewById(R.id.tv_stepsTaken);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            mStepCount= sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            walk=true;
        }
        else{
            tv_steps.setText("no steps");
            walk=false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor==mStepCount){
            stepNum=(int)event.values[0];
            tv_steps.setText(String.valueOf(stepNum));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            sensorManager.registerListener(this,mStepCount,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            sensorManager.unregisterListener(this,mStepCount);
        }

    }
}