package com.example.stepup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WaterActivity extends AppCompatActivity {
    private TextView waterView;
    private int water_count=0;
    private Button btnPlus,btnMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        waterView=findViewById(R.id.waterCountView);
        btnPlus=findViewById(R.id.buttonPlus);
        btnMinus=findViewById(R.id.buttonMinus);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                water_count++;

                waterView.setText(String.valueOf(water_count));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(water_count!=0){
                    water_count--;
                    waterView.setText(String.valueOf(water_count));
                }
                else{
                    waterView.setText("0");
                }
            }
        });
    }
}