package com.mestre.ana.sessio3;

import android.content.ClipData;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ana on 28/01/2017.
 */

public class MusicPlayer extends Fragment {

    View v;

    public MusicPlayer(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v =  inflater.inflate(R.layout.mustic_layout, container, false);

        getActivity().setTitle("Music player");

        return v;
    }
}
