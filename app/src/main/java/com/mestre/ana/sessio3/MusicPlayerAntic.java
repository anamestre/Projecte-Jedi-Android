package com.mestre.ana.sessio3;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mestre.ana.sessio3.MusicPlayer.Song;

import java.util.ArrayList;

/**
 * Created by Ana on 28/01/2017.
 */



public class MusicPlayerAntic extends Fragment implements View.OnClickListener {

    private View v;
    private ImageButton play;
    private MediaPlayer player;
    private ArrayList<Song> mysongs;
    private ContentResolver contentResolver;


    public void getSongs() {
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cur = contentResolver.query(uri, null,
                MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);

        int artist = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int album = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int title = cur.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int id = cur.getColumnIndex(MediaStore.Audio.Media._ID);

        String sArtist = null, sAlbum = null, sTitle = null;
        long lId = 0;
        while (cur.moveToNext()) {
            sArtist = cur.getString(artist);
            sAlbum = cur.getString(album);
            sTitle = cur.getString(title);
            lId = cur.getLong(id);
        }
        Song song = new Song(sTitle, sAlbum, sArtist, lId);
        mysongs.add(song);

    }

    private void getMedia(){
        if(Build.VERSION.SDK_INT >= 23){
            int hasExternalStoragePermission = getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.WRITE_CONTACTS}, 0);
            }
        }
        getSongs();
        /* for(int i = 0; i < mysongs.size(); i++){
            Log.i("holi", "entro al bucle");
            Toast.makeText(getActivity().getBaseContext(), mysongs.get(i).getName(), Toast.LENGTH_SHORT).show();
        } */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v =  inflater.inflate(R.layout.mustic_layout, container, false);
        play = (ImageButton) v.findViewById(R.id.play);
        play.setOnClickListener(this);

        getMedia();

        getActivity().setTitle("Music player");
        return v;
    }


    @Override
    public void onClick(View v){

    }


}
