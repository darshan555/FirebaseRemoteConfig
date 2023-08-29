package com.example.remote_confige_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button loginButton;
    TextView goSignup;
    TextView loginHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        FirebaseRemoteConfig mfirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(60)
                        .build();
        mfirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mfirebaseRemoteConfig.setDefaultsAsync(R.xml.default_login_value);

        mfirebaseRemoteConfig.fetchAndActivate()
                        .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                            @Override
                            public void onComplete(@NonNull Task<Boolean> task) {
                                if(task.isSuccessful()){
                                        boolean updated_successfully = task.getResult();
                                    Log.d("Check " ,"Config params updated: " + updated_successfully);
                                    String text = mfirebaseRemoteConfig.getString("login_button_text");
                                    loginButton.setText(text);
                                    String head_text = mfirebaseRemoteConfig.getString("login_page_heading");
                                    loginHead.setText(head_text);

                                }else {
                                    Toast.makeText(MainActivity.this, "Fetch fail", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        goSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,signUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void findView() {
        email = findViewById(R.id.lemailET);
        password = findViewById(R.id.lpasswordET);
        loginButton = findViewById(R.id.signinBTN);
        goSignup = findViewById(R.id.notaccountTV);
        loginHead = findViewById(R.id.loginHeadTV);
    }
}