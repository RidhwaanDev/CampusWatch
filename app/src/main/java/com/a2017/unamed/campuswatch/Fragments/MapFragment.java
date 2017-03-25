package com.a2017.unamed.campuswatch.Fragments;

import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a2017.unamed.campuswatch.CrimeManager;
import com.a2017.unamed.campuswatch.Model.Crime;
import com.a2017.unamed.campuswatch.R;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ridhwaan on 3/25/17.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback{

    GoogleMap mGoogleMap;
    MapView mMapView;
    private CrimeManager mCrimeManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrimeManager = CrimeManager.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_map_fragment,container,false);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        initializeMap();

        return v;
    }


    private void initializeMap(){

        final LatLng ll = new LatLng(10,10);
            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mGoogleMap = googleMap;
                    googleMap.addMarker(new MarkerOptions().position(ll)).setTitle("hey");
                    googleMap.addMarker(new MarkerOptions().position())
                }
            });

    }

    public List<Crime> getLocationList()


    public LatLng getLocationOfCrime(Crime crime){
        Geocoder geocoder = new Geocoder(getActivity());
        List<android.location.Address> locationResults = null;
        try{
             locationResults = geocoder.getFromLocationName(crime.getmLocation(),1);

        } catch (IOException e){
            e.printStackTrace();
        }

        double lat = locationResults.get(0).getLatitude();
        double lng = locationResults.get(0).getLongitude();

        return new LatLng(lat,lng);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }
}
