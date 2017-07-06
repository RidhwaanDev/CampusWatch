package com.a2017.unamed.campuswatch.SQLiteDB;

/**
 * Created by Ridhwaan on 3/30/17.
 */

public class CrimeBaseSchema {

    public static final class CrimeTable{

        public static final String NAME = "crimes";

        public static final class Cols{

            public static final String UUID = "uuid";
            public static final String NATURE = "nature";
            public static final String LOCATION = "location";
            public static final String DATE = "date";
            public static final String RESULT = "result";
            public static final String CODE = "code";
            public static final String LAT = "lat";
            public static final String LNG = "lng";



        }

    }
}
