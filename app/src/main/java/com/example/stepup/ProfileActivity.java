package com.example.stepup;

import android.content.Intent;
import android.graphics.Color;
import android.icu.number.Precision;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.DecimalFormat;

public class ProfileActivity extends AppCompatActivity  {
    double ans_bmi=0.00,bmr=0.00;
    double height=0,weight=0;
    int age;
    EditText heightText,weightText,ageText;
    TextView textViewBmi,textViewStatus,textViewBMR;
    Button btnSet;
    RadioButton maleRB,femaleRB;

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
        ageText=findViewById(R.id.ageInput);
        maleRB=findViewById(R.id.radioButtonMale);
        femaleRB=findViewById(R.id.radioButtonFemale);
        textViewBMR=findViewById(R.id.textViewBMR);


        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height= Double.valueOf(heightText.getText().toString());
                weight= Double.valueOf(weightText.getText().toString());
                age = Integer.valueOf(ageText.getText().toString());
                takeAnsBmi(height,weight);
                takeAnsCalorieNeeded(height,weight,age);
            }
        });





    }

    public void takeAnsBmi(double height,double weight){
        DecimalFormat df = new DecimalFormat("0.00");
        if(checkWeight()&&checkHeight()){
            ans_bmi= weight/ Math.pow(height,2);
            textViewBmi.setText(String.valueOf(df.format(ans_bmi))+"kg/m2");
            checkStatus(ans_bmi);
        }
    }

    public void takeAnsCalorieNeeded(double height,double weight,int age){
        DecimalFormat df = new DecimalFormat("0.00");

        if(maleRB.isChecked()){
            // BMR = 88.362 + (13.397 x weight in kg) + (4.799 x height in cm) – (5.677 x age
            bmr = 88.362 +(13.397*weight)+(4.799*height*100)-(5.677*age);
        }
        if(femaleRB.isChecked()){
            //BMR = 447.593 + (9.247 x weight in kg) + (3.098 x height in cm) – (4.330 x age in years)
            bmr = 447.593 +(9.247*weight)+(3.098*height*100)-(4.33*age);

        }
        textViewBMR.setText(String.valueOf(df.format(bmr)));

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

    public boolean checkAge(){
        String input = ageText.getText().toString();
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
            textViewStatus.setText("underweight");
            textViewStatus.setTextColor(Color.BLUE);
        }else if (bmi < 25) {
            System.out.println("You are normal");
            textViewStatus.setText("normal");
            textViewStatus.setTextColor(getResources().getColor(R.color.dark_green));

        }else if (bmi < 30) {
            System.out.println("You are overweight");
            textViewStatus.setText("overweight");
            textViewStatus.setTextColor(getResources().getColor(R.color.oren));


        }else {
            System.out.println("You are obese");
            textViewStatus.setText("Obese");
            textViewStatus.setTextColor(Color.RED);

        }
    }


}
