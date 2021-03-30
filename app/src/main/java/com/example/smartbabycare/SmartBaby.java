package com.example.smartbabycare;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

//import com.example.smartbabycare.addingChild.AddingChildFragment;
import com.example.smartbabycare.ui.about.AboutFragment;
import com.example.smartbabycare.ui.home.HomeFragment;
import com.example.smartbabycare.ui.profile.ProfileFragment;
import com.example.smartbabycare.ui.settings.SettingsFragment;
import com.example.smartbabycare.ui.share.ShareFragment;
import com.example.smartbabycare.viewModel.sharedViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SmartBaby extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    private sharedViewModel mainModel;
    NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    //Cloud Messaging
    public static final String NOTIFICATION = "PushNotification";
    DatabaseReference rootDatabase;
    String token;

    private static final String TAG = "SmartBaby";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_baby);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ExtendedFloatingActionButton fab = findViewById(R.id.fab);

        mainModel = new ViewModelProvider(this).get(sharedViewModel.class);
        mainModel.setSharedFab(fab);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);


    //    NavigationView navigationView = findViewById(R.id.nav_view);


        rootDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(SmartBaby.this, "Get Instance Failed", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        token = task.getResult().getToken();
                        FirebaseMessaging.getInstance().subscribeToTopic("All");
                        rootDatabase.child("token").setValue(token);

                        Log.d(TAG,"Token is :"+ token);
                        Toast.makeText(SmartBaby.this, token, Toast.LENGTH_LONG).show();

                    }
                });

        navigationView = findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);



//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(

                R.id.nav_home, R.id.nav_profile, R.id.nav_share, R.id.nav_settings, R.id.nav_about, R.id.nav_exit)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_nav_home_to_addingChildFragment);
            }
        });
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return false;
            }
        });
    }

    private void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_exit:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SmartBaby.this)

                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setTitle("Exit TBoda?")
                        .setMessage("Are you sure you want to Exit")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getApplicationContext(), SmartBabyPhoneLogin.class));
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"You are still Logged in",Toast.LENGTH_LONG).show();
                            }
                        });
                alertDialog.show();




                break;
            default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.smart_baby, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
////
////        if(item.getItemId() == R.id.nav_home) {
////
////            Intent intent = new Intent(SmartBaby.this, HomeFragment.class);
////            finish();
////        }
////        if(item.getItemId() == R.id.nav_profile) {
////
////            Intent intent = new Intent(SmartBaby.this, ProfileFragment.class);
////            finish();
////        }
////        if(item.getItemId() == R.id.nav_share) {
////
////            Intent intent = new Intent(SmartBaby.this, ShareFragment.class);
////            finish();
////        }
////        if(item.getItemId() == R.id.nav_feedback) {
////
////            Intent intent = new Intent(SmartBaby.this, AboutFragment.class);
////            finish();
////        }
////        if(item.getItemId() == R.id.action_settings) {
////
////            Intent intent = new Intent(SmartBaby.this, SettingsFragment.class);
////            finish();
////        }
//        if(item.getItemId() == R.id.nav_exit) {
//
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(SmartBaby.this)
//
//                    .setIcon(R.drawable.ic_baseline_warning_24)
//                    .setTitle("Exit TBoda?")
//                    .setMessage("Are you sure you want to Exit")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            FirebaseAuth.getInstance().signOut();
//                            Intent intent = new Intent(SmartBaby.this, SmartBabyPhoneLogin.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    })
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Toast.makeText(getApplicationContext(),"You are still Logged in",Toast.LENGTH_LONG).show();
//                        }
//                    });
//            alertDialog.show();
//
//
//        }
////
////
////
//       return true;
//   }
}

