package com.mestre.ana.sessio3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ana on 30/01/2017.
 */

public class Ranking extends Fragment {

    private View v;

    public Ranking(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v =  inflater.inflate(R.layout.ranking_layout, container, false);
        getActivity().setTitle("Users ranking");


        return v;
    }

}
