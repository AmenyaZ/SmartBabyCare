package com.example.smartbabycare;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

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

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        final String phonenumber = getIntent().getStringExtra("phonenumber");
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("registration");

                    current_user_db.child(user.getUid()).child("phoneNumber").setValue(phonenumber);

                    AlertDialog.Builder builder = new AlertDialog.Builder(VerifyActivity.this, R.style.Theme_SmartBabyCare);
                    View view = LayoutInflater.from(VerifyActivity.this).inflate(R.layout.success_dialog, null);
                    TextView textView = view.findViewById(R.id.tvSuccess);
                    ImageView imageButton = view.findViewById(R.id.ivSuccessCheck);
                    textView.setText("Login Success!!!"+ "Welcome");

                    imageButton.setImageResource(R.drawable.ic_baseline_check_circle_24);


                    builder.setView(view);
                    builder.show();
                    Intent intent = new Intent(getApplicationContext(),SmartBaby.class);
                    startActivity(intent);

                }
                else {

                    Toast.makeText(VerifyActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();


                }
            }
        });
    }

    private void sendVerificationCode(String phonenumber) {
        progressbar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationId =s;
        }
    }
}