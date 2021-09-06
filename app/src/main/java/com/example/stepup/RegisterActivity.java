package com.example.stepup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText etRegEmail;
    TextInputEditText etRegPassword;
    TextInputEditText etRegName;
    TextView tvLoginHere;
    Button btnRegister;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPass);
        etRegName= findViewById(R.id.etRegName);
        tvLoginHere = findViewById(R.id.tvLoginHere);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("User");

        btnRegister.setOnClickListener(view ->{
            createUser();
            //add DB
        });
        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, loginActivity.class));
        });
    }

    private void createUser() {
        String email = etRegEmail.getText().toString().trim();
        String password = etRegPassword.getText().toString().trim();
        String name = etRegName.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();

        } else if (TextUtils.isEmpty(name)) {
            etRegName.setError("Name cannot be empty");
            etRegName.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        //create DB
                        writeNewUser(name, email);
                        startActivity(new Intent(getApplicationContext(), loginActivity.class));
                    }
//                    } else {
//                        Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }

                }
            });


        }
    }
    public void writeNewUser(String name, String email) {

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email)) {
            String id = mDatabase.push().getKey();
            User user = new User(name, email);

            mDatabase.child(id).setValue(user);
        }
    }
}