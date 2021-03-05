package com.example.smartbabycare.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbabycare.R;
import com.example.smartbabycare.adapter.ChildAdapter;
import com.example.smartbabycare.databinding.FragmentHomeBinding;
import com.example.smartbabycare.model.Child;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding homeBinding;
    private HomeViewModel homeViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private ChildAdapter adapter;
    private ArrayList<Child> children;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = homeBinding.getRoot();

        children = new ArrayList<>();
        children.add(new Child("Evette Dorsile", "2" , "Girl"));
        children.add(new Child("Bruce Ouma", "1" , "Boy"));
        children.add(new Child("BJay Jamal", "4" , "Girl"));
        adapter = new ChildAdapter(children);
        homeBinding.childrenRecyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(requireContext());
        homeBinding.childrenRecyclerView.setLayoutManager(layoutManager);
        homeBinding.childrenRecyclerView.setHasFixedSize(true);


        return root;
    }
}