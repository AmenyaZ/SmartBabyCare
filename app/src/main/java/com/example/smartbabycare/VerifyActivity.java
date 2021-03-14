package com.example.smartbabycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class VerifyActivity extends AppCompatActivity {

    private Button btn_Continue;
    private EditText etCode;
    private ProgressBar progressbar;

    private String verificationId;

    private  FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        btn_Continue = findViewById(R.id.btn_Continue);
        etCode = findViewById(R.id.etCode);
        progressbar = findViewById(R.id.progressbar);

        String phonenumber = getIntent().getStringExtra("phonenumber");
        sendVerificationCode(phonenumber);
        btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerifyActivity.this, SmartBaby.class));
                finish();
            }
        });
    }

    private void sendVerificationCode(String phonenumber) {
    }
}