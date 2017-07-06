package com.a2017.unamed.campuswatch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.a2017.unamed.campuswatch.Model.Crime;
import com.a2017.unamed.campuswatch.SQLiteDB.CrimeBaseHelper;
import com.a2017.unamed.campuswatch.SQLiteDB.CrimeBaseSchema;
import com.a2017.unamed.campuswatch.SQLiteDB.CrimeCursor;

import java.util.ArrayList;

/**
 * Created by Ridhwaan on 3/25/17.
 */

public class CrimeManager {


    private static CrimeManager sCrimeManager;

    private SQLiteDatabase mDataBase;

    private static ArrayList<Crime> crimes = new ArrayList<>();


    public static CrimeManager getInstance(Context c){

        if(sCrimeManager == null){
            sCrimeManager = new CrimeManager(c);
        }
        return sCrimeManager;


    }

    private CrimeManager(Context c){

        mDataBase = new CrimeBaseHelper(c).getWritableDatabase();

    }

    public void addCrimetoDB(Crime crime){

        ContentValues contentValues = getContentValues(crime);

        mDataBase.insert(CrimeBaseSchema.CrimeTable.NAME,null,contentValues);
    }


    private static ContentValues getContentValues(Crime crime){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeBaseSchema.CrimeTable.Cols.UUID,crime.getmUUID().toString());
        contentValues.put(CrimeBaseSchema.CrimeTable.Cols.NATURE,crime.getmNature());
        contentValues.put(CrimeBaseSchema.CrimeTable.Cols.LOCATION,crime.getmLocation());
        contentValues.put(CrimeBaseSchema.CrimeTable.Cols.RESULT,crime.getResult());
        contentValues.put(CrimeBaseSchema.CrimeTable.Cols.CODE,crime.getmCode());
        contentValues.put(CrimeBaseSchema.CrimeTable.Cols.LAT,crime.getmLatLng().toString());
        contentValues.put(CrimeBaseSchema.CrimeTable.Cols.LAT,crime.getLat());
        contentValues.put(CrimeBaseSchema.CrimeTable.Cols.LNG,crime.getLng());



        return contentValues;

    }


    public CrimeCursor queryForCrime(String whichColumn, String[] whichRow){
        Cursor cursor = mDataBase.query(CrimeBaseSchema.CrimeTable.NAME,null,whichColumn,whichRow,null,null,null);

        return new CrimeCursor(cursor);
    }





    public  ArrayList<Crime> getListOfCrimes() {
        return crimes;
    }

    public ArrayList<Crime> getDBCrimes (){


        ArrayList<Crime> crimes = new ArrayList<>();

        CrimeCursor cursor = queryForCrime(null,null);

        try{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                crimes.add(cursor.getCrimeObject());
                cursor.moveToNext();
            }

        }finally {
            cursor.close();
        }

        return crimes;

    }

    public void printCrimes(){
        for (Crime crime: crimes) {
           // Log.d("SINGLETON CRIME MANAGER", "   " + crime.getmNature() + "   " + crime.getmLocation());

        }

    }

}
