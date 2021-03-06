package com.ishan.Cocktailsapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ishan.Cocktailsapp.roomdatabase.FavoriteList;

@Database(entities={FavoriteList.class},version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {


    public abstract FavoriteDao favoriteDao();
}
