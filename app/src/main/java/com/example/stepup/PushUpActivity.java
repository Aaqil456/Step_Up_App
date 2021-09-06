package com.example.stepup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PushUpActivity extends AppCompatActivity {
    TextView total_push_up;
    Button btnPlus;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_up);
        btnPlus=findViewById(R.id.buttonPlus);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                btnPlus.setText(String.valueOf(count));
            }
        });

    }
}