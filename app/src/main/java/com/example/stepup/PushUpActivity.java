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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PushUpActivity extends AppCompatActivity {
    Button btnPlus,btnStop,btnSet;
    private EditText mEditTextInput;
    int counter=0,compare=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_up);
        btnPlus=findViewById(R.id.buttonPlus);
        btnSet=findViewById(R.id.buttonSet);
        btnStop=findViewById(R.id.buttonStop);
        mEditTextInput=findViewById(R.id.editText2);

        //create a date string.
        String date_n = new SimpleDateFormat("dd /MM/ yyyy", Locale.getDefault()).format(new Date());
        //get hold of textview.
        TextView date  = (TextView) findViewById(R.id.date);
        //set it as current date.
        date.setText(date_n);





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
                btnPlus.setText(mEditTextInput.getText().toString());
                btnPlus.setTextSize(80);

                mEditTextInput.setText("");


            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter=Integer.parseInt(btnPlus.getText().toString());
                counter--;
                if(counter>=0){
                btnPlus.setText(String.valueOf(counter));
                }else{
                    btnPlus.setText("0");

                }

            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compare=Integer.parseInt(btnPlus.getText().toString());
                    if (counter !=compare) {
                        Toast.makeText(PushUpActivity.this, "Why so weak", Toast.LENGTH_SHORT).show();

                        btnPlus.setText("TRY AGAIN");
                        btnPlus.setTextSize(20);
                       // counter = Integer.parseInt(mEditTextInput.getText().toString());
                    }else{
                        Toast.makeText(PushUpActivity.this, "Why so strong", Toast.LENGTH_SHORT).show();

                        btnPlus.setText("NICE");
                        btnPlus.setTextSize(20);
                    }


                }

        });


    }
}