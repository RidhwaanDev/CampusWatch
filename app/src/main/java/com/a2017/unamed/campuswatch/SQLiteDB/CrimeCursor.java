package com.a2017.unamed.campuswatch.SQLiteDB;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import com.a2017.unamed.campuswatch.Model.Crime;
import com.google.android.gms.maps.model.LatLng;

import java.util.UUID;

/**
 * Created by Ridhwaan on 3/30/17.
 */

public class CrimeCursor extends CursorWrapper {

    public CrimeCursor(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrimeObject(){


        String id = getString(getColumnIndex(CrimeBaseSchema.CrimeTable.Cols.UUID));
        String nature = getString(getColumnIndex(CrimeBaseSchema.CrimeTable.Cols.NATURE));
        String locaton = getString(getColumnIndex(CrimeBaseSchema.CrimeTable.Cols.LOCATION));
        double lat = getDouble(getColumnIndex(CrimeBaseSchema.CrimeTable.Cols.LAT));
        double lng = getDouble(getColumnIndex(CrimeBaseSchema.CrimeTable.Cols.LNG));

        Crime crime = new Crime(UUID.fromString(id));




        crime.setmNature(nature);
        crime.setmLocation(locaton);

        crime.setLat(lat);
        crime.setLng(lng);


        return crime;
    }
}
