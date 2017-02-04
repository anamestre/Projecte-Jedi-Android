package com.mestre.ana.sessio3.MusicPlayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mestre.ana.sessio3.R;

import java.util.ArrayList;

/**
 * Created by Ana on 04/02/2017.
 */

public class SongAdapter extends BaseAdapter {

    private ArrayList<Song> songs;
    private LayoutInflater songInf;


    public SongAdapter(Context c, ArrayList<Song> theSongs){
        songs=theSongs;
        songInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout songLay = (LinearLayout) songInf.inflate(R.layout.song, parent, false);

        TextView songView = (TextView)songLay.findViewById(R.id.song_title);
        TextView artistView = (TextView)songLay.findViewById(R.id.song_artist);

        Song currSong = songs.get(position);
        songView.setText(currSong.getTitle());
        artistView.setText(currSong.getArtist());

        songLay.setTag(position);
        return songLay;
    }



}
