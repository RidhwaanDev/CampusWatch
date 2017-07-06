package com.a2017.unamed.campuswatch.Model;

import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Ridhwaan on 3/25/17.
 */

public class Crime implements Serializable {


    private String mNature;
    private String mLocation;
    private int mCode;
    private UUID mUUID;
    private double lat;
    private double lng;




    private LatLng mLatLng;

    public Crime(String mNature, String mLocation) {
        this.mNature = mNature;
        this.mLocation = mLocation;
    }
    public Crime() {
        mUUID = UUID.randomUUID();


    }

    public Crime(UUID id){
        this.mUUID = id;
    }

    public UUID getmUUID() {
        return mUUID;
    }
    public String getResult() {
        return "Resolved";
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
    public void setmCode(int code){
        this.mCode = code;
    }

    public int getmCode() {
        return mCode;
    }

    public void setLatLng(LatLng mLatLng){
        this.mLatLng = mLatLng;
    }

    public LatLng getmLatLng(){
        return mLatLng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }



}
