package com.a2017.unamed.campuswatch.ViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.a2017.unamed.campuswatch.Fragments.GraphFragment;
import com.a2017.unamed.campuswatch.Fragments.MapFragment;

import java.util.ArrayList;

/**
 * Created by Ridhwaan on 3/25/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int tabSize = 2;
    private String[] data = {"Map", "Data"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                MapFragment mapFragment = new MapFragment();
                return mapFragment;
            case 1:
                GraphFragment graphFragment = new GraphFragment();
                return graphFragment;

            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data[position];
    }

    @Override
    public int getCount() {
        return tabSize;
    }
}
