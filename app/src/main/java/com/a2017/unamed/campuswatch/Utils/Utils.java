package com.a2017.unamed.campuswatch.Utils;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.a2017.unamed.campuswatch.Model.Crime;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ridhwaan on 3/26/17.
 */

public class Utils {

    public static HashMap<Marker,Crime> sCrimeHashMap = new HashMap<>();

    private static final String TAG = Utils.class.getSimpleName();

    public static final LatLng RUTGERS_GENERAL_COORDINATES =  new LatLng(40.4982, 74.4468);
    public static final String COLLEGE =  "RUTGERS UNIVERSITY";

    public static final int CODE_RED = 1 ;
    public static final int CODE_YELLOW = 2 ;
    public static final int CODE_GREEM = 3 ;






    public static String SanitizeInput (String text){

        if(text.contains("CPN")){
            String newText = text.replace("(CPN)", " ");// + ",New Jersey";
            return newText;
        }
        if(text.contains("Int:")){
            String newText2 = text.replace("Int:", " "); //+  ",New Jersey";
            return newText2;
        }

        return text;

    }

    public static String GeneralizeInput(String text){

        String[] editText = text.split("-");

        return editText[0]; // ",New Jersey";

    }

    public static LatLng getLocationOfCrime(Crime crime, Context context){
        Geocoder geocoder = new Geocoder(context);
        List<Address> locationResults;
        try{


            locationResults = geocoder.getFromLocationName(Utils.SanitizeInput(crime.getmLocation()),1);

            if(locationResults != null){
                if(locationResults.size() != 0) {

                    double lat = locationResults.get(0).getLatitude();
                    double lng = locationResults.get(0).getLongitude();

                    return new LatLng(lat,lng);

                }else{
                    Log.d("GENERALIZE" , "   "  + "GEENRALIZE");
                    locationResults = geocoder.getFromLocationName(Utils.GeneralizeInput(crime.getmLocation()),1);

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



    public static int getCrimeCode(String text){

       if(isRed(text)){
           return Utils.CODE_RED;
       }
        if(isYellow(text)){
            return Utils.CODE_YELLOW;
        }
        if(isGreen(text)){
            return Utils.CODE_GREEM;
        }

        return Utils.CODE_GREEM;
    }


    public static boolean isRed(String text){
        if(text.contains("Kill") || text.contains("Weapon") || text.contains("Burglary")
                || text.contains("Intoxicated") || text.contains("Domestic Violence") || text.contains("Assault")
                ||text.contains("Sexual")){
            return true;
        }
        return false;



    }

    public static boolean isYellow(String text){
        if(text.contains("Possession") || text.contains("Poss") || text.contains("Damage")
                || text.contains("Mischief") || text.contains("theft") || text.contains("Cyber")
                ||text.contains("Arrest") || text.contains("Elude") || text.contains("stolen")||text.contains("credit")){
            return true;
        }

        return false;

    }

    public static boolean isGreen(String text){
        if(text.contains("traffic") || text.contains("suspicious") || text.contains("obstruct")
                || text.contains("annoying") || text.contains("underage")
               ){
            return true;
        }

        return false;
    }









}
