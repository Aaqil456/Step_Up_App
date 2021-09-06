package com.example.stepup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.graphics.Color.BLACK;

public class MainActivity extends AppCompatActivity {

    Button btnLogOut;
    FirebaseAuth mAuth;
    ImageButton imgBtnWalk,imgBtnWater,imgBtnRun,imgBtnPlank,imgBtnPushUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/////Navigation view
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

                        return true;
                    case R.id.weStep:
                        startActivity(new Intent(getApplicationContext(),WeStepActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });



        btnLogOut = findViewById(R.id.btnLogout);
        btnLogOut.setBackgroundColor(0x00000000);
        btnLogOut.setTextColor(BLACK);

        imgBtnWalk=findViewById(R.id.imageWalk);
        imgBtnWater=findViewById(R.id.imageWater);
        imgBtnRun=findViewById(R.id.imageRun);
        imgBtnPlank=findViewById(R.id.imagePlank);
        imgBtnPushUp=findViewById(R.id.imagePushUp);



        mAuth = FirebaseAuth.getInstance();

        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, loginActivity.class));
        });

        imgBtnWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StepCounter.class));
            }
        });

        imgBtnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WaterActivity.class));

            }
        });

        imgBtnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));

            }
        });

        imgBtnPlank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PlankActivity.class));

            }
        });

        imgBtnPushUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PushUpActivity.class));

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, loginActivity.class));
        }
    }
}