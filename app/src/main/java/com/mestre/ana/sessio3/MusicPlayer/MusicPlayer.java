package com.mestre.ana.sessio3.MusicPlayer;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;

import com.mestre.ana.sessio3.BaseActivity;
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
    private MusicController controller;
    private SongAdapter songAdt;

    private boolean paused = false, playbackPaused = false;

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
        setController();

        songAdt = new SongAdapter(getActivity(), songs);
        songView.setAdapter(songAdt);

        Collections.sort(songs, new Comparator<Song>(){
            public int compare(Song a, Song b){
                return a.getTitle().compareTo(b.getTitle());
            }
        });

        return v;
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

    @Override
    public void onDestroy() {
        getActivity().stopService(intent);
        music_service = null;
        super.onDestroy();
    }

    private void setController(){
        if(controller == null) controller = ((BaseActivity) getActivity()).getMusicController();
        controller.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNext();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPrev();
            }
        });

        controller.setMediaPlayer(this);
        controller.setAnchorView(v.findViewById(R.id.song_list));
        controller.setEnabled(true);
    }


    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        if (music_service!=null && bound && music_service.isPng()){
            return music_service.getPosn();
        }
        else return 0;
    }

    @Override
    public int getDuration() {
        if(music_service!=null && bound && music_service.isPng())
            return music_service.getDur();
        else return 0;
    }

    @Override
    public boolean isPlaying() {
        if(music_service!=null && bound)
            return music_service.isPng();
        return false;
    }

    @Override
    public void pause() {
        playbackPaused = true;
        music_service.pausePlayer();
    }

    @Override
    public void seekTo(int pos) {
        music_service.seek(pos);
    }

    @Override
    public void start() {
        music_service.go();
    }

    @Override
    public void onPause(){
        super.onPause();
        paused=true;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(paused){
            setController();
            paused=false;
        }
    }

    @Override
    public void onStop() {
        controller.hide();
        super.onStop();
    }

    private void playNext(){
        music_service.playNext();
        if(playbackPaused){
            setController();
            playbackPaused=false;
        }
        controller.show(0);
    }

    private void playPrev(){
        music_service.playPrev();
        if(playbackPaused){
            setController();
            playbackPaused=false;
        }
        controller.show(0);
    }


    public void songPicked(View view){
        music_service.setSong(Integer.parseInt(view.getTag().toString()));
        music_service.play();
        if(playbackPaused){
            setController();
            playbackPaused = false;
        }
        controller.show(0);
    }




}
