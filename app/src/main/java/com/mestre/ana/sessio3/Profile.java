package com.mestre.ana.sessio3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ana on 27/01/2017.
 */

public class Profile extends Fragment {

    View v;

    public Profile(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v =  inflater.inflate(R.layout.profile_layout, container, false);
        getActivity().setTitle("User profile");

        return v;
    }

}
