package com.a2017.unamed.campuswatch.MapManager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.a2017.unamed.campuswatch.CrimeManager;
import com.a2017.unamed.campuswatch.Model.Crime;
import com.a2017.unamed.campuswatch.Utils.Utils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.VisibleRegion;

/**
 * Created by Ridhwaan on 4/9/17.
 */

public class RegionTask extends AsyncTask<GoogleMap,Void,Void> {



    private GoogleMap mGoogleMap;
    private CrimeManager mCrimeManager;
    private Context context;

    public RegionTask(Context context){
        this.context = context;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(GoogleMap... params) {
        mGoogleMap = params[0];
        mCrimeManager = CrimeManager.getInstance(context);
        Log.d("REGION TASK", "   " +   " TEST ");
        process();




        return null;
    }

    public void process() {
        for (Marker marker : Utils.sCrimeHashMap.keySet()) {

            if (isOnMap(marker)) {
                mGoogleMap.clear();
                } else {
                marker.setVisible(false);
            }


        }
    }

    public boolean isOnMap(Marker marker){


        VisibleRegion visibleReg = mGoogleMap.getProjection().getVisibleRegion();
        LatLngBounds bounds = visibleReg.latLngBounds;

        if(bounds.contains(marker.getPosition())){
            Log.d("MARKER VISIBLE",  "   " + " MARKER IS VISIBLE");
        return true;

        } else {
            Log.d("MARKER INVISIBLE",  "   " + " MARKER IS INVISIBLE");
            // git test
            marker.setVisible(false);

            return false;
        }

    }

}
