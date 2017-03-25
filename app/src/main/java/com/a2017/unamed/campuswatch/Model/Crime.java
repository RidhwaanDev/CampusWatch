package com.a2017.unamed.campuswatch.Model;

/**
 * Created by Ridhwaan on 3/25/17.
 */

public class Crime {


    private String mNature;
    private String mLocation;

    public Crime(String mNature, String mLocation) {
        this.mNature = mNature;
        this.mLocation = mLocation;
    }
    public Crime() {

    }


    public String getmNature() {
        return mNature;
    }

    public void setmNature(String mNature) {
        this.mNature = mNature;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }
}
