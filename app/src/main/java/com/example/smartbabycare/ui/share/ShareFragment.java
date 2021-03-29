package com.example.smartbabycare.ui.share;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartbabycare.R;
import com.example.smartbabycare.ui.about.AboutViewModel;
import com.example.smartbabycare.ui.home.HomeFragment;

import java.util.Objects;

import com.example.smartbabycare.R;

public class ShareFragment extends Fragment {


    private ImageView facebook;
    private ImageView twitter;
    private ImageView google;
    private ImageView linkedin;
    private ImageView whatsapp;
    private ImageView mail;
    private ProgressBar progressBar;


    private ShareViewModel mViewModel;

    public static ShareFragment newInstance(){

        return new ShareFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_nav_share, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShareViewModel.class);
        // TODO: Use the ViewModel
    }

}

//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_nav_share);
//
//        facebook = findViewById(R.id.img_facebook);
//        twitter = findViewById(R.id.img_twitter);
//        google = findViewById(R.id.img_google);
//        linkedin = findViewById(R.id.img_linkedin);
//        whatsapp = findViewById(R.id.img_whatsapp);
//        mail = findViewById(R.id.img_gmail);
//        progressBar = findViewById(R.id.simpleProgressBar);
//
//        facebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent link=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/"));
//                startActivity(link);
//            }
//        });
//
//        twitter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent link=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.twitter.com/"));
//                startActivity(link);
//            }
//        });
//        google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent link=new Intent(Intent.ACTION_VIEW, Uri.parse("https://myaccount.google.com/"));
//                startActivity(link);
//            }
//        });
//        linkedin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent link=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/"));
//                startActivity(link);
//            }
//        });
//        whatsapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent link=new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/.com/"));
//                startActivity(link);
//            }
//        });
//        mail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent link=new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com/mail/u/0/#inbox?compose=new"));
//                startActivity(link);
//            }
//        });
//
//    }
//}

//    public static ShareFragment newInstance() {
//        return new ShareFragment();
//
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//
//
//        return inflater.inflate(R.layout.fragment_nav_share, container, false);
//
//
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(ShareViewModel.class);
//        // TODO: Use the ViewModel
//    }
//
//}
