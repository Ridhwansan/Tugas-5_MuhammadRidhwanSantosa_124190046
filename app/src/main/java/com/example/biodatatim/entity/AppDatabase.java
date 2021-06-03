package com.example.biodatatim.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataTeam.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataTeamDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase inidb(Context context){
        if (appDatabase==null)
            appDatabase = Room.databaseBuilder(context,AppDatabase.class, "dbTeam").allowMainThreadQueries().build();
        return appDatabase;
    }
}

