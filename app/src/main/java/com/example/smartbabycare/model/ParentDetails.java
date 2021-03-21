package com.example.smartbabycare.model;

public class ParentDetails {
    public  String mName, mEmail, mDoB,mPhonenumber;

    public ParentDetails() {
    }

    public ParentDetails(String mName, String mEmail, String mDoB, String mPhonenumber) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mDoB = mDoB;
        this.mPhonenumber = mPhonenumber;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmDoB(String mDoB) {
        this.mDoB = mDoB;
    }

    public void setmPhonenumber(String mPhonenumber) {
        this.mPhonenumber = mPhonenumber;
    }
}
