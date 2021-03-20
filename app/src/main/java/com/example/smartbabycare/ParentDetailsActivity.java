package com.example.smartbabycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.smartbabycare.model.ParentDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParentDetailsActivity extends AppCompatActivity {
    EditText et_Name,et_email,etPhone_Number,etDoB;
    Button btnSubmit;
    ImageView profile;
    Uri image_uri;

    private DatabaseReference mDatabase;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userid = user.getUid();

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    int TAKE_IMAGE_CODE = 10001;
    private FirebaseAuth mAuth;
    public String mName, mEmail, lastName, mPhonenumber, mDoB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_details);
        et_Name = findViewById(R.id.et_Name);
        et_email = findViewById(R.id.et_email);
        etPhone_Number = findViewById(R.id.etPhone_Number);
        etDoB = findViewById(R.id.etDoB);
        btnSubmit = findViewById(R.id.btnSubmit);
        profile = findViewById(R.id.profile);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("registration").child(userid);

        mDatabase.orderByChild("phoneNumber").equalTo(mPhonenumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mPhonenumber = snapshot.child("phoneNumber").getValue().toString();
                etPhone_Number.setText(mPhonenumber);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveParentsDetails();

            }
        });




    }


    private void saveParentsDetails() {

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String mName = et_Name.getText().toString();
                final String mEmail = et_email.getText().toString();
                final  String mDoB = etDoB.getText().toString();
                final  String mPhonenumber = etPhone_Number.getText().toString();

                ParentDetails parentDetails = new ParentDetails(
                        mName,
                        mEmail,
                        mDoB,
                        mPhonenumber


                );

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userId = user.getUid();

                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("registration").child(userId);
                current_user_db.setValue(parentDetails);

                AlertDialog.Builder builder = new AlertDialog.Builder(ParentDetailsActivity.this);
                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage("User Profile Successfully Updated!!!\n"+"Welcome"+ et_Name.getText().toString()) .setTitle("Profile Update");

                //Setting message manually and performing action on button click
                builder.setMessage("User Profile Successfully Updated!!!\n"+"Welcome"+ et_Name.getText().toString())
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(getApplicationContext(), SmartBaby.class));
                                finish();

                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Profile Update");
                alert.show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
