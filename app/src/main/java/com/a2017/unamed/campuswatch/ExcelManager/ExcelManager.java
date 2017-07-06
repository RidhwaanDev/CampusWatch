package com.a2017.unamed.campuswatch.ExcelManager;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.a2017.unamed.campuswatch.CrimeManager;
import com.a2017.unamed.campuswatch.Model.Crime;
import com.a2017.unamed.campuswatch.Utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Created by Ridhwaan on 3/25/17.
 */

public class ExcelManager {

    private ArrayList<Cell[]> cellList;
    private ArrayList<Crime> crimesList;

    private CrimeManager mCrimeManager;

    private static final String TAG = ExcelManager.class.getSimpleName();


    public ExcelManager (Context c){
            cellList = new ArrayList<>();
        mCrimeManager = CrimeManager.getInstance(c);
        crimesList = mCrimeManager.getListOfCrimes();
        testMethod(c);
    }

    public void testMethod(Context c){

        AssetManager assetManager = c.getAssets();

        String[] fileNames;
        try{

            fileNames = assetManager.list("data_set");
            InputStream is = assetManager.open("data_set/jan_data_set_2_2017.xls");
            Workbook workbook = Workbook.getWorkbook(is);

//New code
            for (int i = 0; i < workbook.getNumberOfSheets() ; i++) {
                Sheet s = workbook.getSheet(i);

                //Sheet s =  workbook.getSheet(3);
                int row = s.getRows();
                int columns = s.getColumns();

                Log.d("TAG", "CELL INFO   " + row + " " + columns);


                for (int r = 0; r <  row; r++) {

                    Cell[] cells = s.getRow(r);
                    cellList.add(cells);

                    for (int d = 0; d < columns ; d++) {


                        Cell cell = s.getCell(d,r);



                        String cellContents = cell.getContents().toString();


                        //  Log.d("ERROR TAG" , "  " + r  + "  " + d);
                        //  Log.d("TAG", "CELL TEXT   " + cellContents);

                    }
                }


                for(Cell[] cell : cellList){

                    Crime crime = new Crime();
                    for (int z = 0; z < cell.length; z++) {
                        if(z == 1){
                            crime.setmNature(cell[z].getContents());

                            String crimeName = cell[z].getContents();
                            Log.d(TAG," crime name is   " + crimeName + " code for crime is    " + Utils.getCrimeCode(crimeName));
                            crime.setmCode(Utils.getCrimeCode(crimeName));

                            //categorize crime
                        }
                        if(z == 4){
                            crime.setmLocation(cell[z].getContents());
                        }
                    }
                    crimesList.add(crime);
                }


            }

            //new code
          /*  Sheet s =  workbook.getSheet(3);
            int row = s.getRows();
            int columns = s.getColumns();

            Log.d("TAG", "CELL INFO   " + row + " " + columns);


            for (int r = 0; r <  row; r++) {

                Cell[] cells = s.getRow(r);
                cellList.add(cells);

                for (int d = 0; d < columns ; d++) {


                    Cell cell = s.getCell(d,r);



                    String cellContents = cell.getContents().toString();


                  //  Log.d("ERROR TAG" , "  " + r  + "  " + d);
                  //  Log.d("TAG", "CELL TEXT   " + cellContents);

                }
            }


            for(Cell[] cell : cellList){

                Crime crime = new Crime();
                for (int i = 0; i < cell.length; i++) {
                    if(i == 1){
                        crime.setmNature(cell[i].getContents());

                        String crimeName = cell[i].getContents();
                        Log.d(TAG," crime name is   " + crimeName + " code for crime is    " + Utils.getCrimeCode(crimeName));
                        crime.setmCode(Utils.getCrimeCode(crimeName));




                        //categorize crime
                    }
                    if(i == 4){
                        crime.setmLocation(cell[i].getContents());
                    }
                }
                crimesList.add(crime);
            }  */


            mCrimeManager.printCrimes();



        }catch (IOException e){
            e.printStackTrace();
        } catch (BiffException b){
            b.printStackTrace();
        }


    }


}
