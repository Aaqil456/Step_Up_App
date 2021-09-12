package com.example.stepup;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StepCounter extends AppCompatActivity {

    private TextView distanceView,calorieView;
    private Button textView;
    private double MagnitudePrevious = 0;
    private Integer stepCount = 0;
    private double calorieCount=0;
    private double distanceCount=0;
    Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        //create a date string.
        String date_n = new SimpleDateFormat("dd /MM/ yyyy", Locale.getDefault()).format(new Date());
        //get hold of textview.
        TextView date  = (TextView) findViewById(R.id.date);
        //set it as current date.
        date.setText(date_n);





        textView = findViewById(R.id.tv_stepsTaken);
        distanceView = findViewById(R.id.distanceNumView);
        calorieView = findViewById(R.id.CalorieBurnView);
        resetBtn=findViewById(R.id.btnReset);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                DecimalFormat df = new DecimalFormat("0.00");
                if (sensorEvent!= null){
                    float x_acceleration = sensorEvent.values[0];
                    float y_acceleration = sensorEvent.values[1];
                    float z_acceleration = sensorEvent.values[2];

                    double Magnitude = Math.sqrt(x_acceleration*x_acceleration + y_acceleration*y_acceleration + z_acceleration*z_acceleration);
                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;

                    if (MagnitudeDelta > 6){
                        stepCount++;
                        calorieCount=stepCount*0.04;
                        distanceCount = stepCount * 0.066666666;
                    }
                    textView.setText(stepCount.toString());
                    calorieView.setText(String.valueOf(df.format(calorieCount)));
                    distanceView.setText(String.valueOf(df.format(distanceCount)));


                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }


        };
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
                textView.setText("0");
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.putFloat("calorieCount", (float) calorieCount);
                editor.putFloat("calorieCount", (float) distanceCount);


                calorieCount=sharedPreferences.getInt("0",0);
                distanceCount=sharedPreferences.getInt("0",0);
                editor.apply();
            }
        });


        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.apply();
    }

    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        stepCount=sharedPreferences.getInt("0",0);
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.apply();
    }

    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount", 0);
    }
}