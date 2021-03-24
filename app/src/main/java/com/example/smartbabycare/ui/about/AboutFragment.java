package com.example.smartbabycare.ui.about;
//
//import androidx.lifecycle.ViewModelProvider;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.smartbabycare.R;
//
//public class AboutFragment extends Fragment {
//
//    private AboutViewModel mViewModel;
//
//    public static AboutFragment newInstance() {
//        return new AboutFragment();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_nav_about, container, false);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(AboutViewModel.class);
//        // TODO: Use the ViewModel
//    }
//
//}


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartbabycare.R;
import com.example.smartbabycare.ui.home.HomeFragment;


import java.util.Objects;

public class AboutFragment extends AppCompatActivity {
    private ImageView btn_backHome;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nav_about);

        btn_backHome= findViewById(R.id.iv_back);
        progressBar= findViewById(R.id.simpleProgressBar);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).hide();

        btn_backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(AboutFragment.this, HomeFragment.class);
                startActivity(intent);
                finish();
                return;
//
//                startActivity(new Intent(getApplicationContext(), HomeFragment.class));
//                finish();
            }
        });
    }
}