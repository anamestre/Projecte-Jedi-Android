package com.mestre.ana.sessio3.MusicPlayer;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ana on 04/02/2017.
 */

public class MusicPlayerService extends Service implements MediaPlayer.OnPreparedListener
{


    private MediaPlayer player;
    private ArrayList<Song> songs;
    private int songPosn;
    private final IBinder musicBind = new MusicBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    public void onCreate(){
        super.onCreate();

        ini();
    }

    public void ini(){
        songPosn = 0;
        player = new MediaPlayer();

        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);

        player.setOnPreparedListener(this);
        //player.setOnCompletionListener(this);
        //player.setOnErrorListener(this);

    }

    public void setList(ArrayList<Song> songs){
        songs = songs;
    }

    public class MusicBinder extends Binder {
        MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent){
        player.stop();
        player.release();
        return false;
    }

    public void play(){
        player.reset();
        Song playSong = songs.get(songPosn);
        long currSong = playSong.getId();
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                currSong);
        try{
            player.setDataSource(getApplicationContext(), trackUri);
        }
        catch(Exception e){
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }
        player.prepareAsync();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }


    //@Override
    public boolean onError (MediaPlayer mp, int what, int extra){
        return true;
    }


    public void setSong(int songIndex){
        songPosn = songIndex;
    }

}
