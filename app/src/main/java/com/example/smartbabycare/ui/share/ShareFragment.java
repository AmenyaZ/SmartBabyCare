package com.example.smartbabycare.ui.share;
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
//public class ShareFragment extends Fragment {
//
//    private ShareViewModel mViewModel;
//
//    public static ShareFragment newInstance() {
//        return new ShareFragment();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_nav_share, container, false);
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

public class ShareFragment extends AppCompatActivity {
    private ImageView btn_backHome;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nav_about);

    }
}