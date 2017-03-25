package com.a2017.unamed.campuswatch.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a2017.unamed.campuswatch.ExcelManager.ExcelManager;
import com.a2017.unamed.campuswatch.R;

/**
 * Created by Ridhwaan on 3/25/17.
 */

public class GraphFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_graph_fragment,container,false);

        ExcelManager e = new ExcelManager(getActivity());
        return v;
    }



}
