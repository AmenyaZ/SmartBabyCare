package com.example.smartbabycare.model;

public class ParentDetails {
    public  String mName, mEmail, DoB,mPhonenumber;

    public ParentDetails() {
    }

    public ParentDetails(String mName, String mEmail, String doB, String mPhonenumber) {
        this.mName = mName;
        this.mEmail = mEmail;
        DoB = doB;
        this.mPhonenumber = mPhonenumber;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public String getmPhonenumber() {
        return mPhonenumber;
    }

    public void setmPhonenumber(String mPhonenumber) {
        this.mPhonenumber = mPhonenumber;
    }
}
