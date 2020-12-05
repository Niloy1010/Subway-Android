package com.example.firehousesubsapp.SIngletonHandler;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.firehousesubsapp.Interfaces.OrderDao;
import com.example.firehousesubsapp.Pojo.Order;

@Database(entities = Order.class,exportSchema = false,version = 1)
public abstract class OrderSingleton extends RoomDatabase {
    private static final String DB_NAME = "order_db";
    private static OrderSingleton instance;

    public static OrderSingleton getInstance(Context context) {
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),OrderSingleton.class,DB_NAME).allowMainThreadQueries().build();

        }
        return instance;
    }
    public abstract OrderDao orderDao() ;


}
