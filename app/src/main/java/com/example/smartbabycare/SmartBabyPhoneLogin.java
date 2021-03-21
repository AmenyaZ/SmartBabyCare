package com.example.smartbabycare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smartbabycare.model.CountryData;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class SmartBabyPhoneLogin extends AppCompatActivity {

    private Spinner spinnerCountries;
    private EditText etPhoneNumber;

    private Button btn_login;

    private ProgressBar progressbar;

    CoordinatorLayout coordinatorLayout;

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
                if (isConnected()) {
                    String code = CountryData.countryAreaCodes[spinnerCountries.getSelectedItemPosition()];

                    String number = etPhoneNumber.getText().toString().trim();

                    if (number.isEmpty() || number.length() < 10) {

                        etPhoneNumber.setError("Valid number is required");
                        etPhoneNumber.requestFocus();
                        return;
                    }

                    String phoneNumber = "+" + code + number;



                    Intent intent = new Intent(SmartBabyPhoneLogin.this, VerifyActivity.class);
                    intent.putExtra("phonenumber", phoneNumber);
                    startActivity(intent);
                    finish();
                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(view, "No network Connectivity detected, Try Again!!!!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }
    private boolean isConnected() {

        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            //NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(this, SmartBabyPhoneLogin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}