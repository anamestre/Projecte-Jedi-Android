package com.mestre.ana.sessio3.MusicPlayer;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import com.mestre.ana.sessio3.MusicPlayer.MusicPlayerService.MusicBinder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Binder;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

import com.mestre.ana.sessio3.R;

/**
 * Created by Ana on 04/02/2017.
 */

public class MusicPlayer extends Fragment implements MediaPlayerControl {

    private View v;
    private ArrayList<Song> songs;
    private ListView songView;

    private MusicPlayerService music_service;
    private Intent intent;
    private boolean bound = false;

    public MusicPlayer(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = inflater.inflate(R.layout.music_list_layout, container, false);
        getActivity().setTitle("Music player");

        ini();
        getSongs();

        SongAdapter songAdt = new SongAdapter(getActivity(), songs);
        songView.setAdapter(songAdt);

        Collections.sort(songs, new Comparator<Song>(){
            public int compare(Song a, Song b){
                return a.getTitle().compareTo(b.getTitle());
            }
        });

        return v;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_shuffle:
                getActivity().stopService(intent);
                music_service = null;
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicBinder binder = (MusicBinder) service;
            music_service = binder.getService();
            music_service.setList(songs);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };


    public void ini(){
        songView = (ListView) v.findViewById(R.id.song_list);
        songs = new ArrayList<Song>();
    }

    public void getSongs() {

        ContentResolver musicResolver = getActivity().getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int albumColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);

            while (musicCursor.moveToNext()){
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                String thisAlbum = musicCursor.getString(albumColumn);
                songs.add(new Song(thisTitle, thisAlbum, thisArtist, thisId));
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(intent == null){
            intent = new Intent(getActivity(), MusicPlayerService.class);
            getActivity().bindService(intent, musicConnection, Context.BIND_AUTO_CREATE);
            getActivity().startService(intent);
        }
    }

    public void songPicked(View view){
        music_service.setSong(Integer.parseInt(view.getTag().toString()));
        music_service.play();
    }

    @Override
    public void onDestroy() {
        getActivity().stopService(intent);
        music_service = null;
        super.onDestroy();
    }


}
