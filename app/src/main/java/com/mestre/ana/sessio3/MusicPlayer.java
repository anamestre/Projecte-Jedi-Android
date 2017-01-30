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

class Mp3Filter implements FilenameFilter{
    public boolean accept(File file, String name){
        return (name.endsWith(".mp3"));
    }
}

public class MusicPlayer extends Fragment implements View.OnClickListener {

    private View v;
    private ImageButton play;
    private MediaPlayer player;
    //private MediaCursorAdapter adapter;
    private boolean hasStarted;

    private ArrayList<Song> mysongs;
    ContentResolver contentResolver;

    private ArrayList<String> songNames;



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

    public void getSongs(){
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cur = contentResolver.query(uri, null,
                MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);

        int artist = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int album = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int title = cur.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int id = cur.getColumnIndex(MediaStore.Audio.Media._ID);

        String sArtist, sAlbum, sTitle;
        long lId;
        while(cur.moveToNext()){
            sArtist = cur.getString(artist);
            sAlbum = cur.getString(album);
            sTitle = cur.getString(title);
            lId = cur.getLong(id);

            Song song = new Song(sTitle, sAlbum, sArtist, lId);
            mysongs.add(song);

        }
    }

    @Override
    public void onClick(View v){

    }


}
