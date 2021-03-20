package com.example.smartbabycare;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

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
    }
}