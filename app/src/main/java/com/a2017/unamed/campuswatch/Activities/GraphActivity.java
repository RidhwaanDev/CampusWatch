package com.a2017.unamed.campuswatch.Activities;

import android.support.v4.app.Fragment;

import com.a2017.unamed.campuswatch.Fragments.GraphFragment;
import com.a2017.unamed.campuswatch.SingleFragmentActivity;

/**
 * Created by Ridhwaan on 3/25/17.
 */

public class GraphActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new GraphFragment();
    }
}
