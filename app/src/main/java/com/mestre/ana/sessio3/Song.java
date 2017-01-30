package com.mestre.ana.sessio3;

import android.content.ContentUris;
import android.net.Uri;

/**
 * Created by Ana on 29/01/2017.
 */

public class Song {
    private String title;
    private String album;
    private String artist;
    private long id;

    public Song(String title, String album, String artist, long id){
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.id = id;
    }

    public Uri getURI() {
        return ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
    }
}
