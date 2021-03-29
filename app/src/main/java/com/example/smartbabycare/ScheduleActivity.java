package com.example.smartbabycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScheduleActivity extends AppCompatActivity {
    private CheckBox bcg,hep1, opv0, dtwp, hib1,hep2,opv1;
    private EditText datebcg, datehep1,dateopv0,datedtwp,datehib1,datehep2,dateopv1;
    private Button save;
    TextView date_given2, date_given3;

    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    String userid=user.getUid();
    private DatabaseReference mDatabase;
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
        save = (Button) findViewById(R.id.save);


       /* val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("Preferences", 0)
        val phoneNo : String? = sharedPreferences.getString("phone", null)
        reference = Firebase.database.getReference("ChildRecords").child(phoneNo!!)  */

        /*SharedPreferences pref = getApplicationContext().getSharedPreferences("Preferences", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("phone", phoneNo);*/

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String  phoneNo = sharedPreferences.getString("phone", null) ;

        mDatabase = FirebaseDatabase.getInstance().getReference("ChildRecords").child(phoneNo);
        getChildsDate();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer OUTPUT = new StringBuffer();
                OUTPUT.append("BCG :")
                        .append(bcg.isChecked());

                OUTPUT.append("\nHEP_B1 :")
                        .append(hep1.isChecked());

                OUTPUT.append("\nOPV_0 :")
                        .append(opv0.isChecked());

                OUTPUT.append("\nDTWP_1 :")
                        .append(dtwp.isChecked());

                OUTPUT.append("\nHIB_1 :")
                        .append(hib1.isChecked());

                OUTPUT.append("\nHEP_B2 :")
                        .append(hep2.isChecked());

                OUTPUT.append("\nOPV_1 :")
                        .append(opv1.isChecked());

                Toast.makeText(ScheduleActivity.this, OUTPUT.toString(), Toast.LENGTH_LONG).show();




            }
        });
    }

    private void getChildsDate() {
    }
}