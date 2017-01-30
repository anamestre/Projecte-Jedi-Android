package com.mestre.ana.sessio3;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Ana on 28/01/2017.
 */



public class MusicPlayer extends Fragment implements View.OnClickListener {

    private View v;
    private ImageButton play;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v =  inflater.inflate(R.layout.mustic_layout, container, false);
        play = (ImageButton) v.findViewById(R.id.play);
        play.setOnClickListener(this);

        getActivity().setTitle("Music player");
        return v;
    }


    @Override
    public void onClick(View v){

    }


}
