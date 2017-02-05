package com.mestre.ana.sessio3.DB;

/**
 * Created by Ana on 30/01/2017.
 */

public class User {
    private long _id;
    private String username;
    private int points;
    private String id_photo;

    public String getId_photo() {
        return id_photo;
    }

    public void setId_photo(String id_photo) {
        this.id_photo = id_photo;
    }

    public long get_id() {

        return _id;
    }

    public void set_id(long _id) {

        this._id = _id;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }



}
