package com.example.first.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.first.model.Car;

import java.util.List;

@Dao
public interface CarDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Car... cars);

    @Insert
    void add(Car car);

    @Delete
    void delete(Car car);

    @Update
    void update(Car car);

    @Query("SELECT * FROM cars")
    List<Car> getAll();

    @Query("SELECT * FROM cars WHERE id=:id")
    Car get(int id);

}
