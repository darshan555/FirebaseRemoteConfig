package com.example.remote_confige_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signUpActivity extends AppCompatActivity {
    EditText email,password;
    Button signupButton;
    TextView goLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupButton = findViewById(R.id.signupBTN);
        goLogin = findViewById(R.id.alreadyAccTV);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(signUpActivity.this, "Created account successfully", Toast.LENGTH_SHORT).show();
            }
        });

        goLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signUpActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}