package com.a2017.unamed.campuswatch.MapManager;

import android.graphics.Color;
import android.util.Log;

import com.a2017.unamed.campuswatch.Model.Crime;
import com.a2017.unamed.campuswatch.Utils.Utils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Ridhwaan on 3/28/17.
 */

public class CircleClickListener implements GoogleMap.OnCircleClickListener, GoogleMap.OnMapClickListener {

    private final GoogleMap mGoogleMap;
    private Marker marker;

    public CircleClickListener(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }

    @Override
    public void onCircleClick(Circle circle) {
        Log.d("CRIME CIRCLE", "    " +  " ENTERED");



        Crime crime = (Crime) circle.getTag();

        if(marker!= null){
            marker.remove();
        }

        marker = mGoogleMap.addMarker(new MarkerOptions()
                .position(circle.getCenter())
                .title(crime.getmNature()));

        Utils.sCrimeHashMap.put(marker,crime);

        marker.showInfoWindow();

    }

    @Override
    public void onMapClick(LatLng latLng) {
        marker.remove();
    }
}
