package com.example.smartbabycare.viewModel;

import androidx.lifecycle.ViewModel;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class sharedViewModel extends ViewModel {
    private ExtendedFloatingActionButton sharedFab;

    public ExtendedFloatingActionButton getSharedFab() {
        return sharedFab;
    }

    public void setSharedFab(ExtendedFloatingActionButton sharedFab) {
        this.sharedFab = sharedFab;
    }
}
