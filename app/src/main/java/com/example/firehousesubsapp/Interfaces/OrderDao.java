package com.example.firehousesubsapp.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firehousesubsapp.Pojo.Order;

import java.util.List;

@Dao
public interface OrderDao {
    @Query("select * from orders")
    List<Order> getOrderList();

    @Insert
    void insertOrder(Order order);

    @Update
    void updateOrder(Order order);

    @Query("UPDATE orders SET quantity=:quantity WHERE id = :id")
    void updateCurrentOrder(int quantity, int id);

    @Query("Delete from orders")
    void deleteAllOrder();

    @Delete
    void deleteOrder(Order order);
}
