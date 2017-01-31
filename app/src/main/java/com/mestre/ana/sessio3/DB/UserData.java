package com.mestre.ana.sessio3.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ana on 30/01/2017.
 */

public class UserData {

    private SQLiteDatabase database;

    // Helper to manipulate table
    private MySQLiteHelper dbHelper;


    // Here we select the appropriate columns (all columns)
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_USERNAME, MySQLiteHelper.COLUMN_POINTS, MySQLiteHelper.COLUMN_ID_PHOTO};

    public UserData(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public User createUser(String username, int id_photo, int points) {
        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_USERNAME, username);
        values.put(MySQLiteHelper.COLUMN_POINTS, points);
        values.put(MySQLiteHelper.COLUMN_ID_PHOTO, id_photo);

        // Actual insertion of the data using the values variable
        long insertId = database.insert(MySQLiteHelper.TABLE_USERS, null, values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();

        return newUser;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.set_id(cursor.getLong(0));
        user.setUsername(cursor.getString(1));
        user.setPoints(cursor.getInt(2));
        user.setId_photo(cursor.getInt(3));
        return user;
    }

    public List<User> getUsersOrderByRanking(){
        List<User> users = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
                allColumns, null, null, null, null, MySQLiteHelper.COLUMN_POINTS + " DESC", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return users;
    }

    public void createHardcodedUsers() {
        createUser("Ana Mestre", 0, 125);
        createUser("ChuloIsMyPerro", 1, 80);
        createUser("Juanpetón", 2, 120);
    }

}
