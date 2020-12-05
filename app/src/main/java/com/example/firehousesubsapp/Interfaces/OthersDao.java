package com.example.firehousesubsapp.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firehousesubsapp.Pojo.Order;
import com.example.firehousesubsapp.Pojo.Others;

import java.util.List;

@Dao
public interface OthersDao {
    @Query("select * from others")
    List<Others> getOthersList();

    @Insert
    void insertOthers(Others other);

    @Update
    void updateOthers(Others other);

    @Query("UPDATE others SET name=:name WHERE id = :id")
    void updateCurrentOthers(int name, int id);

    @Query("Delete from others")
    void deleteAllOthers();

    @Delete
    void deleteOthers(Others other);
}
