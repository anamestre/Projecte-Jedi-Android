package com.mestre.ana.sessio3;

import android.content.ClipData;
import android.content.Intent;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Ana on 28/01/2017.
 */

public class MusicPlayer extends Fragment {

    private View v;
    private ImageButton play;
    private MediaPlayer player;
    private boolean hasStarted;
    private String song;
    private ArrayList<File> mysongs;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v =  inflater.inflate(R.layout.mustic_layout, container, false);
        //playMedia();

        //mysongs = songs(Environment.getExternalStorageDirectory());
        //mysongs = songs(getActivity().getBaseContext().getExternalFilesDir());

        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            mysongs = songs(Environment.getExternalStorageDirectory());

            for(int i = 0; i < mysongs.size(); i++){
                Log.i("holi", "entro al bucle");
                Toast.makeText(getActivity().getBaseContext(), mysongs.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        }




        getActivity().setTitle("Music player");

        return v;
    }

    public ArrayList<File> songs(File root){
        ArrayList<File> list = new ArrayList<>();
        File[] songs = root.listFiles();
        for(File f: songs){
            if(f.isDirectory()) list.addAll(songs(f));
            else{
                if(f.getName().endsWith(".mp3")){
                    list.add(f);
                }
            }
        }
        return list;
    }

    /*public void playMedia() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = MediaStore.Audio.Media("external");
        intent.setData(uri);

    }*/
}
