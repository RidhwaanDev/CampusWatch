package com.a2017.unamed.campuswatch;

import android.content.Context;
import android.util.Log;

import com.a2017.unamed.campuswatch.Model.Crime;

import java.util.ArrayList;

/**
 * Created by Ridhwaan on 3/25/17.
 */

public class CrimeManager {


    private static CrimeManager sCrimeManager;

    private static ArrayList<Crime> crimes = new ArrayList<>();


    public static CrimeManager getInstance(Context c){

        if(sCrimeManager == null){
            sCrimeManager = new CrimeManager(c);
        }
        return sCrimeManager;


    }

    private CrimeManager(Context c){


    }

    public  ArrayList<Crime> getListOfCrimes() {
        return crimes;
    }

    public void printCrimes(){
        for (Crime crime: crimes) {
            Log.d("SINGLETON CRIME MANAGER", "   " + crime.getmNature() + "   " + crime.getmLocation());

        }

    }

}
