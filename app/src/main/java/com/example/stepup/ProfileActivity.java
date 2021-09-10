package com.example.stepup;

import android.content.Intent;
import android.icu.number.Precision;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.DecimalFormat;

public class ProfileActivity extends AppCompatActivity  {
    double ans_bmi=0.00;
    double height=0,weight=0;
    EditText heightText,weightText;
    TextView textViewBmi,textViewStatus;
    Button btnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //navbar
        //Initialize and assign var
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        //perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:

                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.weStep:
                        startActivity(new Intent(getApplicationContext(),WeStepActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });
        //navbar code until here
        btnSet=findViewById(R.id.buttonSet);
        textViewBmi=findViewById(R.id.TextViewBMICalculate);
        textViewStatus=findViewById(R.id.TextViewBMIStatus);
        heightText=findViewById(R.id.heightInput);
        weightText=findViewById(R.id.weightInput);


        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height= Double.valueOf(heightText.getText().toString());
                weight= Double.valueOf(weightText.getText().toString());
                takeAns(height,weight);
            }
        });





    }

    public void takeAns(double height,double weight){
        DecimalFormat df = new DecimalFormat("0.00");
        if(checkWeight()&&checkHeight()){
            ans_bmi= weight/ Math.pow(height,2);
            textViewBmi.setText(String.valueOf(df.format(ans_bmi))+"kg/m2");
            checkStatus(ans_bmi);

        }

    }

    public boolean checkWeight(){
        String input = weightText.getText().toString();
        if (input.length() == 0) {
            Toast.makeText(ProfileActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        double millisInput = Double.parseDouble(input) * 60000;
        if (millisInput == 0) {
            Toast.makeText(ProfileActivity.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
            return false;
        }

        return  true;
    }

    public boolean checkHeight(){
        String input = heightText.getText().toString();
        if (input.length() == 0) {
            Toast.makeText(ProfileActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        double millisInput = Double.parseDouble(input) * 60000;
        if (millisInput == 0) {
            Toast.makeText(ProfileActivity.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
            return false;
        }

        return  true;
    }

    // Prints BMI category
    public void checkStatus(double bmi) {
        if(bmi < 18.5) {
            System.out.println("You are underweight");
            textViewStatus.setText("underWeight");
        }else if (bmi < 25) {
            System.out.println("You are normal");
            textViewStatus.setText("normal");
        }else if (bmi < 30) {
            System.out.println("You are overweight");
            textViewStatus.setText("overweight");

        }else {
            System.out.println("You are obese");
            textViewStatus.setText("Obese");
        }
    }


}
