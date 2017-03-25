package com.a2017.unamed.campuswatch.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.a2017.unamed.campuswatch.ExcelManager.ExcelManager;
import com.a2017.unamed.campuswatch.Fragments.MapFragment;
import com.a2017.unamed.campuswatch.R;
import com.a2017.unamed.campuswatch.SingleFragmentActivity;

public class MapActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment() {
        return new MapFragment();
    }
}
