package com.mestre.ana.sessio3.DB;

/**
 * Created by Ana on 30/01/2017.
 */

public class User {
    private long _id;
    private String username;
    private int points;
    private int id_photo;

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
