package com.example.smartbabycare.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartbabycare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {


    ImageView profile,edit;
    TextView name,designation,dob,gender,mobileNumber,email;


    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nav_profile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

    private DatabaseReference mDatabase;

    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    String userid=user.getUid();

    public String imageUri,mName,mDesignation,phoneNumber,mEmail,mDob,mGender;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* ImageView profile,edit;
        TextView name,designation,dob,gender,mobileNumber,email;*/

        profile = view.findViewById(R.id.profile);
        edit = view.findViewById(R.id.edit);
        name = view.findViewById(R.id.name);
        designation = view.findViewById(R.id.designation);
        dob = view.findViewById(R.id.dob);
        gender = view.findViewById(R.id.gender);
        mobileNumber = view.findViewById(R.id.mobileNumber);
        email = view.findViewById(R.id.email);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("registration").child(userid);
        getUserProfile();


    }

    private void getUserProfile() {

        mDatabase.orderByChild("name").equalTo(mName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mName=snapshot.child("mName").getValue().toString();
                phoneNumber=snapshot.child("mPhonenumber").getValue().toString();
                mDob=snapshot.child("mDoB").getValue().toString();
                mEmail=snapshot.child("mEmail").getValue().toString();

                name.setText(mName);
                mobileNumber.setText(phoneNumber);
                dob.setText(mDob);
                email.setText(mEmail);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}