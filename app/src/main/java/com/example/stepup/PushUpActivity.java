package com.example.stepup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PushUpActivity extends AppCompatActivity {
    Button btnPlus,btnStop,btnSet;
    private EditText mEditTextInput;
    int count=0,compare=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_up);
        btnPlus=findViewById(R.id.buttonPlus);
        btnSet=findViewById(R.id.buttonSet);
        btnStop=findViewById(R.id.buttonStop);
        mEditTextInput=findViewById(R.id.editText2);



        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                btnPlus.setText(String.valueOf(count));

            }
        });



        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditTextInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(PushUpActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(PushUpActivity.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }
                compare=Integer.parseInt(mEditTextInput.getText().toString());
                mEditTextInput.setText("");
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (count <compare ) {
                        Toast.makeText(PushUpActivity.this, "Why so weak", Toast.LENGTH_SHORT).show();

                        btnPlus.setText("0");
                        count = 0;
                    }


                }

        });


    }
}