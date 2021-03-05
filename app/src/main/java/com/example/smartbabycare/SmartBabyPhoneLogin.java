package com.example.smartbabycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.smartbabycare.model.CountryData;

public class SmartBabyPhoneLogin extends AppCompatActivity {

    private Spinner spinnerCountries;
    private EditText etPhoneNumber;

    private Button btn_login;

    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_baby_phone_login);

        spinnerCountries = findViewById(R.id.spinnerCountries);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btn_login = findViewById(R.id.btn_login);
        progressbar = findViewById(R.id.progressbar);

        spinnerCountries.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(this, VerifyActivity.class));
                finish();
            }
        });
    }
}