package com.example.firehousesubsapp.SIngletonHandler;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.firehousesubsapp.Interfaces.OthersDao;
import com.example.firehousesubsapp.Pojo.Others;

@Database(entities = Others.class,exportSchema = false,version = 1)
public abstract class OthersSingleton extends RoomDatabase {
    private static final String DB_NAME = "Others_db";
    private static OthersSingleton instance;

    public static OthersSingleton getInstance(Context context) {
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),OthersSingleton.class,DB_NAME).allowMainThreadQueries().build();

        }
        return instance;
    }
    public abstract OthersDao OthersDao() ;


}
