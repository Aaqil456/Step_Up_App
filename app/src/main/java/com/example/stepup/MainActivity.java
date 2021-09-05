package com.example.stepup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.graphics.Color.BLACK;

public class MainActivity extends AppCompatActivity {

    Button btnLogOut;
    FirebaseAuth mAuth;
    ImageButton imgBtnWalk,imgBtnWater,imgBtnRun,imgBtnPlank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //remove actionbar and titlebar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
//        //until here

        setContentView(R.layout.activity_main);

        btnLogOut = findViewById(R.id.btnLogout);
        btnLogOut.setBackgroundColor(0x00000000);
        btnLogOut.setTextColor(BLACK);

        imgBtnWalk=findViewById(R.id.imageWalk);
        imgBtnWater=findViewById(R.id.imageWater);
        imgBtnRun=findViewById(R.id.imageRun);
        imgBtnPlank=findViewById(R.id.imagePlank);


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