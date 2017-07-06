package com.a2017.unamed.campuswatch.SQLiteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.a2017.*;

/**
 * Created by Ridhwaan on 3/30/17.
 */

public class CrimeBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    private static final String DATABASE_NAME = "crimebase.db";

    public CrimeBaseHelper ( Context c){
        super(c,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + CrimeBaseSchema.CrimeTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                CrimeBaseSchema.CrimeTable.Cols.UUID + ", " +
                CrimeBaseSchema.CrimeTable.Cols.NATURE + ", " +
                CrimeBaseSchema.CrimeTable.Cols.LOCATION + ", " +
                CrimeBaseSchema.CrimeTable.Cols.RESULT  + ", " +
                CrimeBaseSchema.CrimeTable.Cols.CODE  + ", " +
                CrimeBaseSchema.CrimeTable.Cols.LAT + " , " +
                CrimeBaseSchema.CrimeTable.Cols.LNG +
                ")"

        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
