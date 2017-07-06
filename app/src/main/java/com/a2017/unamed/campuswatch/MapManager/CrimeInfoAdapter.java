package com.a2017.unamed.campuswatch.MapManager;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.a2017.unamed.campuswatch.CrimeManager;
import com.a2017.unamed.campuswatch.Model.Crime;
import com.a2017.unamed.campuswatch.R;
import com.a2017.unamed.campuswatch.Utils.Utils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;

/**
 * Created by Ridhwaan on 3/28/17.
 */

public class CrimeInfoAdapter implements GoogleMap.InfoWindowAdapter {


    private LayoutInflater mLayoutInflater;
    private CrimeManager mCrimeManager;
    private Context c;
   // private BottomSheetDialog bottomSheetDialog;

    public CrimeInfoAdapter (LayoutInflater layoutInflater, Context c){
        this.mLayoutInflater = layoutInflater;
       // mCrimeManager = CrimeManager.getInstance(c);
        this.c = c;
     //   this.bottomSheetDialog = bottomSheetDialog;


    }


    @Override
    public View getInfoWindow(Marker marker) {



        View v = mLayoutInflater.inflate(R.layout.info_window_layout,null);


        TextView crimeTitle = (TextView) v.findViewById(R.id.crime_name_view);
        TextView crimeLocation = (TextView) v.findViewById(R.id.crime_location_view);
        TextView crimeDate = (TextView) v.findViewById(R.id.crime_date);
        TextView crimeStatus = (TextView) v.findViewById(R.id.crime_status);

        RadioButton crimeCodeRed = (RadioButton) v.findViewById(R.id.crime_code_red);
        RadioButton crimeCodeYellow = (RadioButton) v.findViewById(R.id.crime_code_yellow);
        RadioButton crimeCodeGreen = (RadioButton) v.findViewById(R.id.crime_code_green);



        crimeTitle.setText(marker.getTitle());

        Crime crime = Utils.sCrimeHashMap.get(marker);
        crimeLocation.setText(crime.getmLocation());

        switch(crime.getmCode()){

            case Utils.CODE_RED:

                crimeCodeRed.setChecked(true);

                break;
            case Utils.CODE_YELLOW:


                crimeCodeYellow.setChecked(true);

                break;
            case Utils.CODE_GREEM:

                crimeCodeGreen.setChecked(true);

                break;

            default:

                return null;


        }



        return v;
    }

    @Override
    public View getInfoContents(Marker marker) {




        return null;
    }
}
