package com.example.smartbabycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;
import android.app.DatePickerDialog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ScheduleActivity extends AppCompatActivity {
    private CheckBox bcg,hep1, opv0, dtwp, hib1,hep2,opv1;
    private EditText datebcg, datehep1,dateopv0,datedtwp,datehib1,datehep2,dateopv1;
    private Button btnsave;
    TextView date_given2, date_given3;
    private int mYear, mMonth, mDay;

    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    String userid=user.getUid();
    private DatabaseReference mDatabase;

    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        datebcg =  findViewById(R.id.datebcg);
        datehep1 =  findViewById(R.id.datehep1);
        dateopv0 =  findViewById(R.id.dateopv0);
        datedtwp =  findViewById(R.id.datedtwp);
        datedtwp =  findViewById(R.id.datedtwp);
        datehib1 =  findViewById(R.id.datehib1);
        datehep2 =  findViewById(R.id.datehep2);
        dateopv1 =  findViewById(R.id.dateopv1);

        date_given2 =  findViewById(R.id.date_given2);
        date_given3 =  findViewById(R.id.date_given3);



        bcg = (CheckBox) findViewById(R.id.bcg);
        hep1 = (CheckBox) findViewById(R.id.hep1);
        opv0 = (CheckBox) findViewById(R.id.opv0);
        dtwp = (CheckBox) findViewById(R.id.dtwp);
        hib1 = (CheckBox) findViewById(R.id.hib1);
        hep2 = (CheckBox) findViewById(R.id.hep2);
        opv1 = (CheckBox) findViewById(R.id.opv1);
        btnsave = findViewById(R.id.btnsave);





        datebcg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int year = myCalendar.get(Calendar.YEAR);
                final int month =myCalendar.get(Calendar.MONTH);
                final int day =myCalendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog= new DatePickerDialog(ScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        datebcg.setText(day+"/"+month+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();


            }
        });
        datehep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int year = myCalendar.get(Calendar.YEAR);
                final int month =myCalendar.get(Calendar.MONTH);
                final int day =myCalendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog= new DatePickerDialog(ScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        datehep1.setText(day+"/"+month+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();


            }
        });
        dateopv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int year = myCalendar.get(Calendar.YEAR);
                final int month =myCalendar.get(Calendar.MONTH);
                final int day =myCalendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog= new DatePickerDialog(ScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        dateopv0.setText(day+"/"+month+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();


            }


        });
        datedtwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int year = myCalendar.get(Calendar.YEAR);
                final int month =myCalendar.get(Calendar.MONTH);
                final int day =myCalendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog= new DatePickerDialog(ScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        datedtwp.setText(day+"/"+month+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();


            }
        });
        datehib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int year = myCalendar.get(Calendar.YEAR);
                final int month =myCalendar.get(Calendar.MONTH);
                final int day =myCalendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog= new DatePickerDialog(ScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        datehib1.setText(day+"/"+month+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();


            }
        });
        datehep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int year = myCalendar.get(Calendar.YEAR);
                final int month =myCalendar.get(Calendar.MONTH);
                final int day =myCalendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog= new DatePickerDialog(ScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        datehep2.setText(day+"/"+month+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();


            }
        });
        dateopv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int year = myCalendar.get(Calendar.YEAR);
                final int month =myCalendar.get(Calendar.MONTH);
                final int day =myCalendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog= new DatePickerDialog(ScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        dateopv1.setText(day+"/"+month+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();


            }
        });


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String  phoneNo = sharedPreferences.getString("phone", null) ;


        //String mKey = mDatabase.push().getKey();
        mDatabase = FirebaseDatabase.getInstance().getReference("Dates");


        getChildsDate();


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleActivity.this, R.style.Theme_SmartBabyCare);
                View view1 = LayoutInflater.from(ScheduleActivity.this).inflate(R.layout.success_dialog1, null);
                TextView textView = view1.findViewById(R.id.tvSuccess);
                ImageView imageButton = view1.findViewById(R.id.ivSuccessCheck);
                textView.setText("Vaccine Successfully Scheduled!!!");
                Button button = view1.findViewById(R.id.buttonSave);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        saveChildsVaccinationDatesChanges();
                        startActivity(new Intent(getApplicationContext(), SmartBaby.class));
                        finish();
                    }
                });


                imageButton.setImageResource(R.drawable.ic_baseline_check_circle_24);

                builder.setView(view1);
                builder.show();
            }
        });

    }



    /*private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        datebcg.setText(sdf.format(myCalendar.getTime()));
    }*/

    String mDobs,ScheduleDate,mDatebcg,mDatehep1,mDateopv0,mdatedtwp,mdatehib1,mdatehep2,mdateopv1;
    private void getChildsDate() {

        mDatabase.orderByChild("DateOfBirth1").equalTo(mDobs).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mDobs= snapshot.child("DateOfBirth1").getValue().toString();
                ScheduleDate = snapshot.child("ScheduleDate1").getValue().toString();

                //Tobe administered at birth
                mDatebcg = snapshot.child("datebcg").getValue().toString();
                mDatehep1 = snapshot.child("datehep1").getValue().toString();
                mDateopv0 = snapshot.child("dateopv0").getValue().toString();

                //To be givien After 6 Weeks
                mdatedtwp = snapshot.child("datedtwp").getValue().toString();
                mdatehib1 = snapshot.child("datehib1").getValue().toString();
                mdatehep2 = snapshot.child("datehep2").getValue().toString();
                mdateopv1 = snapshot.child("dateopv1").getValue().toString();

                //DefaultDate
                date_given3.setText("The Ideal Date is :"+" "+mDobs);
                date_given2.setText("The Ideal Date is :"+" "+ScheduleDate);

                //At Bith
                datebcg.setText(mDatebcg);
                datehep1.setText(mDatehep1);
                dateopv0.setText(mDateopv0);

                //After 6 weeks
                datedtwp.setText(mdatedtwp);
                datehib1.setText(mdatehib1);
                datehep2.setText(mdatehep2);
                dateopv1.setText(mdateopv1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void saveChildsVaccinationDatesChanges() {

        if (isDatebcg() || isDatehep1() || isDateopv0() || isDatedtwp() || isDatehib1() || isDatehep2() || isDateopv1()){
            
        }

    }

    private boolean isDateopv1() {
        if (!mdateopv1.equals(dateopv1.getText().toString())){

            mDatabase.child("firstName").setValue(dateopv1.getText().toString());

            mdateopv1 = dateopv1.getText().toString();
            return true;
        }
        else {
            return  false;
        }
    }

    private boolean isDatehep2() {
        if (!mdatehep2.equals(datehep2.getText().toString())){

            mDatabase.child("datehep2").setValue(datehep2.getText().toString());

            mdatehep2 = datehep2.getText().toString();
            return true;
        }
        else {
            return  false;
        }
    }

    private boolean isDatehib1() {
        if (!mdatehib1.equals(datehib1.getText().toString())){

            mDatabase.child("datehib1").setValue(datehib1.getText().toString());

            mdatehib1 = datehib1.getText().toString();
            return true;
        }
        else {
            return  false;
        }
    }

    private boolean isDatedtwp() {
        if (!mdatedtwp.equals(datedtwp.getText().toString())){

            mDatabase.child("datedtwp").setValue(datedtwp.getText().toString());

            mdatedtwp = datedtwp.getText().toString();
            return true;
        }
        else {
            return  false;
        }
    }

    private boolean isDateopv0() {
        if (!mDateopv0.equals(dateopv0.getText().toString())){

            mDatabase.child("dateopv0").setValue(dateopv0.getText().toString());

            mDateopv0 = dateopv0.getText().toString();
            return true;
        }
        else {
            return  false;
        }
    }

    private boolean isDatehep1() {
        if (!mDatehep1.equals(datehep1.getText().toString())){

            mDatabase.child("datehep1").setValue(datehep1.getText().toString());

            mDatehep1 = datehep1.getText().toString();
            return true;
        }
        else {
            return  false;
        }
    }

    private boolean isDatebcg() {
        if (!mDatebcg.equals(datebcg.getText().toString())){

            mDatabase.child("datebcg").setValue(datebcg.getText().toString());

            mDatebcg = datebcg.getText().toString();
            return true;
        }
        else {
            return  false;
        }
    }
}