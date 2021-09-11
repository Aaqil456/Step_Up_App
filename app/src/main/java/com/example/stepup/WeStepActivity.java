package com.example.stepup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class WeStepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_step);

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
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.weStep:

                        return true;

                }

                return false;
            }
        });




    }
    public void fit1(View view){
        Intent weStepIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chloeting.com/program/2021/get-fit-challenge.html"));
        startActivity(weStepIntent);
    }

    public void fit2(View view){
        Intent weStepIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chloeting.com/program/2021/weight-loss-challenge.html"));
        startActivity(weStepIntent);
    }

    public void fit3(View view){
        Intent weStepIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chloeting.com/program/2021/2-weeks-shred-challenge.html"));
        startActivity(weStepIntent);
    }

    public void fit4(View view){
        Intent weStepIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chloeting.com/program/2021/flat-stomach-challenge.html"));
        startActivity(weStepIntent);
    }

    public void fit5(View view){
        Intent weStepIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chloeting.com/program/2019/lean-arms-challenge.html"));
        startActivity(weStepIntent);
    }

    public void fit6(View view){
        Intent weStepIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chloeting.com/program/2019/lean-thigh-challenge.html"));
        startActivity(weStepIntent);
    }

    public void fit7(View view){
        Intent weStepIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://chloeting.com/program/2020/intense-core-challenge.html"));
        startActivity(weStepIntent);
    }
    public void moreChall(View view){
        Intent weStepIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://chloeting.com/program/"));
        startActivity(weStepIntent);
    }
}