package com.example.smartbabycare.viewModel;

import androidx.lifecycle.ViewModel;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class sharedViewModel extends ViewModel {
    private FloatingActionButton sharedFab;

    public FloatingActionButton getSharedFab() {
        return sharedFab;
    }

    public void setSharedFab(FloatingActionButton sharedFab) {
        this.sharedFab = sharedFab;
    }
}
