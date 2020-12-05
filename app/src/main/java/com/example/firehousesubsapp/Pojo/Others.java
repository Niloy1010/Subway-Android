package com.example.firehousesubsapp.Pojo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "others")
public class Others {


    @PrimaryKey(autoGenerate = true)
       public     int id;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Others(String name)  {
        this.name = name;
    }
    public Others() {

    }
}
