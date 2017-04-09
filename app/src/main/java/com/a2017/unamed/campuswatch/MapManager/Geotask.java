package com.a2017.unamed.campuswatch.MapManager;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import com.a2017.unamed.campuswatch.CrimeManager;
import com.a2017.unamed.campuswatch.Model.Crime;
import com.a2017.unamed.campuswatch.Utils.Utils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ridhwaan on 3/30/17.
 */

public class Geotask extends AsyncTask<GoogleMap,Void,Void> {


    private Context mContext;
    private CrimeManager mCrimeManager;

    private GoogleMap mGoogleMap;
    Geocoder geocoder;

    public Geotask(Context context) {
        this.mContext = context;
        geocoder = new Geocoder(mContext);
        mCrimeManager = CrimeManager.getInstance(context);
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        addMarkersFrom(mGoogleMap);

        List<Crime> dbCrimes = mCrimeManager.getDBCrimes();


        for(Crime crime: dbCrimes){
            Log.d("GEO LOCATE" , "  db debugging " + crime.getLat() + "  " + crime.getLng());
        }


    }

    @Override
    protected Void doInBackground(GoogleMap... params) {

        this.mGoogleMap = params[0];

        runGeoLocater();


        return null;
    }

    public void runGeoLocater() {


        List<Crime> crimes = mCrimeManager.getListOfCrimes();


        if (mCrimeManager.getDBCrimes().size() == 0 ){

            for (Crime crime : crimes) {

                try {

                    List<Address> listOfLocations = geocoder.getFromLocationName(Utils.SanitizeInput(crime.getmLocation()), 1);

                    if (listOfLocations != null) {
                        if (listOfLocations.size() != 0) {

                            double lat = listOfLocations.get(0).getLatitude();
                            double lng = listOfLocations.get(0).getLongitude();

                            crime.setLatLng(new LatLng(lat, lng));
                            crime.setLat(lat);
                            crime.setLng(lng);
                            mCrimeManager.addCrimetoDB(crime);

                        } else {
                            Log.d("GENERALIZING", "   " + crime.getmLocation());

                            listOfLocations = geocoder.getFromLocationName(Utils.SanitizeInput(Utils.GeneralizeInput(crime.getmLocation())), 1);


                            if (listOfLocations.size() == 0) {
                                listOfLocations = geocoder.getFromLocationName(Utils.COLLEGE, 1);
                                double lat = listOfLocations.get(0).getLatitude();
                                double lng = listOfLocations.get(0).getLongitude();
                                crime.setLat(lat);
                                crime.setLng(lng);

                                crime.setLatLng(new LatLng(lat, lng));
                                mCrimeManager.addCrimetoDB(crime);

                            }

                        }

                        double lat = listOfLocations.get(0).getLatitude();
                        double lng = listOfLocations.get(0).getLongitude();
                        crime.setLat(lat);
                        crime.setLng(lng);

                        crime.setLatLng(new LatLng(lat, lng));
                        mCrimeManager.addCrimetoDB(crime);


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


        }
    }

    public void addMarkersFrom(GoogleMap googleMap){

        List<Crime> crimes = mCrimeManager.getDBCrimes();



        for (Crime crime: crimes) {

            LatLng latLng = new LatLng(crime.getLat(), crime.getLng());

            Log.d("TEST","  LAT LNG   " +latLng.toString());


            Circle circle = googleMap.addCircle(new CircleOptions()
                    .clickable(true)
                    .radius(5)
                    .center(latLng));

            circle.setTag(crime);

            crime.setmCode(Utils.getCrimeCode(crime.getmNature()));


            Log.d("TEST" , "    " + crime.getmCode());
            switch (crime.getmCode()){
                case Utils.CODE_RED:
                    circle.setFillColor(0x33FF0000);
                    circle.setStrokeColor(0x33FF0000);
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
