package com.a2017.unamed.campuswatch.Fragments;

import android.graphics.Color;
import android.location.Geocoder;
import android.location.Address;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.a2017.unamed.campuswatch.CrimeManager;
import com.a2017.unamed.campuswatch.ExcelManager.ExcelManager;
import com.a2017.unamed.campuswatch.MapManager.CircleClickListener;
import com.a2017.unamed.campuswatch.MapManager.CrimeInfoAdapter;
import com.a2017.unamed.campuswatch.MapManager.Geotask;
import com.a2017.unamed.campuswatch.MapManager.RegionTask;
import com.a2017.unamed.campuswatch.Model.Crime;
import com.a2017.unamed.campuswatch.R;
import com.a2017.unamed.campuswatch.Utils.Utils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.VisibleRegion;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.a2017.unamed.campuswatch.Utils.Utils.sCrimeHashMap;

/**
 * Created by Ridhwaan on 3/25/17.
 */

public class MapFragment extends Fragment {

    GoogleMap mGoogleMap;
    GoogleMapOptions mGoogleMapOptions;
    MapView mMapView;
    private CrimeManager mCrimeManager;
    private HashMap<Marker,Crime> crimeHashMap = new HashMap<>();


    private static final String TAG = MapFragment.class.getSimpleName();


    private LayoutInflater mLayoutInflater;
    private BottomSheetBehavior mBottomSheetBehavior;
    private LinearLayout mLinearLayout;
    Geocoder geocoder;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGoogleMapOptions = new GoogleMapOptions().liteMode(true).rotateGesturesEnabled(false);
        mCrimeManager = CrimeManager.getInstance(getActivity());
       // geocoder = new Geocoder(getActivity());
        ExcelManager e = new ExcelManager(getActivity());








    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_map_fragment,container,false);

       View bottomSheetView = v.findViewById(R.id.bottom_sheet);
        bottomSheetView.setVisibility(View.INVISIBLE);


        mLinearLayout =(LinearLayout) v.findViewById(R.id.bottom_sheet_linear_layout);


        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView);
        mBottomSheetBehavior.setPeekHeight(400);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);


       /* mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            }
        });*/


        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();


        mLayoutInflater = inflater;

        initializeMap();

        return v;
    }


    private void initializeMap(){

            mMapView.getMapAsync(new OnMapReadyCallback()  {
                @Override
                public void onMapReady(final GoogleMap googleMap) {
                    mGoogleMap = googleMap;

                    if(mGoogleMap != null){

                     goToLocation();



                        mGoogleMap.setInfoWindowAdapter(new CrimeInfoAdapter(mLayoutInflater,getActivity()));
                        mGoogleMap.setOnCircleClickListener(new CircleClickListener(mGoogleMap));
                        MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(getActivity(),R.raw.map_style);
                        mGoogleMap.setMapStyle(mapStyleOptions);


                        mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                            @Override
                            public void onMapLoaded() {



                                     Geotask g = new Geotask(getActivity());
                                     g.execute(mGoogleMap);





                                       // mGoogleMap.setMyLocationEnabled(true);




                              // addMarkersFrom(mGoogleMap);

                                //addMarkers(mGoogleMap);

                            }
                        });



                    }

                }
            });

    }

    private void goToLocation(){
            LatLng latLng = new LatLng(40.4982, -74.4468);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,15);
            mGoogleMap.moveCamera(cameraUpdate);
    }


    public void addMarkersFrom(GoogleMap googleMap){
        List<Crime> crimes = mCrimeManager.getListOfCrimes();



        for (Crime crime: crimes) {

            Log.d(TAG,"  LAT LNG   " + crime.getmLatLng().toString());


            Circle circle = googleMap.addCircle(new CircleOptions()
                    .clickable(true)
                    .radius(5)
                    .center(crime.getmLatLng()));

            circle.setTag(crime);
        }
    }





    public void addMarkers(GoogleMap googleMap){

        List<Crime> crimes = mCrimeManager.getListOfCrimes();
        Log.d("CRIME SIZE TAG", "   " + crimes.size());

        for(Crime crime:crimes){
            if(!(getLocationOfCrime(crime) == null)) {

                      /* Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                                    .position(getLocationOfCrime(crime))
                                    .title(crime.getmNature()));
                                   marker.setVisible(false);*/

                Circle circle = googleMap.addCircle(new CircleOptions()
                       .clickable(true)
                        .radius(5)
                        .center(getLocationOfCrime(crime)));
                circle.setTag(crime);




               Log.d(TAG, "    " + crime.getmCode());
                switch (crime.getmCode()){
                    case Utils.CODE_RED:
                        circle.setFillColor(Color.RED);
                        circle.setStrokeColor(Color.RED);
                        break;
                    case Utils.CODE_YELLOW:
                        circle.setFillColor(Color.YELLOW);
                        circle.setStrokeColor(Color.YELLOW);
                        break;
                    case Utils.CODE_GREEM:
                        circle.setFillColor(Color.GREEN);
                        circle.setStrokeColor(Color.GREEN);
                        break;
                    default: circle.setFillColor(Color.BLACK);

                }

            }

        }
    }






    public LatLng getLocationOfCrime(Crime crime){

        List<Address> locationResults;
        try{


            locationResults = geocoder.getFromLocationName(Utils.SanitizeInput(crime.getmLocation()),1);

            if(locationResults != null){
                if(locationResults.size() != 0) {

                    double lat = locationResults.get(0).getLatitude();
                    double lng = locationResults.get(0).getLongitude();

                    return new LatLng(lat,lng);

                }else{
                    Log.d("GENERALIZING" , "   "  + crime.getmLocation() );

                    locationResults = geocoder.getFromLocationName(Utils.SanitizeInput(Utils.GeneralizeInput(crime.getmLocation())),1);

                        if (locationResults.size() == 0){
                            locationResults = geocoder.getFromLocationName(Utils.COLLEGE,1);

                            double lat = locationResults.get(0).getLatitude();
                            double lng = locationResults.get(0).getLongitude();

                            return new LatLng(lat,lng);


                        }

                        double lat = locationResults.get(0).getLatitude();
                        double lng = locationResults.get(0).getLongitude();

                        return new LatLng(lat,lng);

                }

            }





        } catch (IOException e){
            e.printStackTrace();
        }
        return null;


    }





}
