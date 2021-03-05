package com.example.smartbabycare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class VerifyActivity extends AppCompatActivity {

    private Button btn_Continue;
    private EditText etCode;
    private ProgressBar progressbar;

    private String verificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
    }
}