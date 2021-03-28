package com.example.smartbabycare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

//import com.example.smartbabycare.addingChild.AddingChildFragment;
import com.example.smartbabycare.ui.about.AboutFragment;
import com.example.smartbabycare.ui.home.HomeFragment;
import com.example.smartbabycare.ui.profile.ProfileFragment;
import com.example.smartbabycare.ui.settings.SettingsFragment;
import com.example.smartbabycare.ui.share.ShareFragment;
import com.example.smartbabycare.viewModel.sharedViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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


        navigationView = findViewById(R.id.nav_view);


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

//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//
//        if(item.getItemId() == R.id.nav_home) {
//
//            Intent intent = new Intent(SmartBaby.this, HomeFragment.class);
//            finish();
//        }
//        if(item.getItemId() == R.id.nav_profile) {
//
//            Intent intent = new Intent(SmartBaby.this, ProfileFragment.class);
//            finish();
//        }
//        if(item.getItemId() == R.id.nav_share) {
//
//            Intent intent = new Intent(SmartBaby.this, ShareFragment.class);
//            finish();
//        }
//        if(item.getItemId() == R.id.nav_feedback) {
//
//            Intent intent = new Intent(SmartBaby.this, AboutFragment.class);
//            finish();
//        }
//        if(item.getItemId() == R.id.action_settings) {
//
//            Intent intent = new Intent(SmartBaby.this, SettingsFragment.class);
//            finish();
//        }
//        if(item.getItemId() == R.id.nav_exit) {
//
//            Intent intent = new Intent(SmartBaby.this, SmartBabyPhoneLogin.class);
//            finish();
//        }
//
//
//
//        return true;
//    }
}

