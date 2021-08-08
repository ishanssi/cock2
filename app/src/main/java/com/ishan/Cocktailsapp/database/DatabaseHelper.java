package com.ishan.Cocktailsapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";
    // User table name
    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String COLUMN_ING_ID = "user_id";
    private static final String COLUMN_ING_NAME = "user_name";
    private static final String COLUMN_ING_IMGURL = "user_email";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_ING_ID + " INTEGER PRIMARY KEY ," + COLUMN_ING_NAME + " TEXT,"
            + COLUMN_ING_IMGURL + " TEXT)";
    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        // Create tables again
        onCreate(db);
    }
    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(cocktaildbitems user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ING_ID, user.getId());
        values.put(COLUMN_ING_NAME, user.getName());
        values.put(COLUMN_ING_IMGURL, user.getImageurl());
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<cocktaildbitems> getAllUser() {
        // array of columns to fetch
        String[] columns = {

                COLUMN_ING_ID,
                COLUMN_ING_NAME,
                COLUMN_ING_IMGURL
        };
        // sorting orders
        String sortOrder =
                COLUMN_ING_NAME+ " ASC";
        List<cocktaildbitems> userList = new ArrayList<cocktaildbitems>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                cocktaildbitems user = new cocktaildbitems();
               user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ING_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_ING_NAME)));
                user.setImageurl(cursor.getString(cursor.getColumnIndex(COLUMN_ING_IMGURL)));


                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }
    /**
     * This method to update user record
     *
     * @param user
     */

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(cocktaildbitems user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_ING_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    /**
     * This method to check user exist or not
     *
     * @param ID
     * @return true/false
     */
    public boolean checkUser(String ID) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_ING_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_ING_NAME + " = ?";
        // selection argument
        String[] selectionArgs = {ID};
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

}