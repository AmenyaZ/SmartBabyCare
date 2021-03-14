package com.example.smartbabycare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class VerifyActivity extends AppCompatActivity {

    private Button btn_Continue;
    private EditText etCode;
    private ProgressBar progressbar;

    private String verificationId;

    private  FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

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
                String code = etCode.getText().toString().trim();

                if (code.isEmpty() || code.length() < 6) {

                    etCode.setError("Enter code...");
                    etCode.requestFocus();
                    return;
                }
                progressbar.setVisibility(View.VISIBLE);
                verifyCode(code);

            }
        });
    }

    private void sendVerificationCode(String phonenumber) {
    }
}