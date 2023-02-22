package com.karan.savecontact.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TBLCategory.class,TBLContact.class},exportSchema = false,version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    private static final String DB_NAME ="ContactDetail";
    private static   DatabaseHelper instance;
    public abstract DatabaseInterface databaseInterface();


    public static synchronized  DatabaseHelper databaseHelper(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),DatabaseHelper.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
