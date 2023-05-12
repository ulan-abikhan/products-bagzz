package com.example.first.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cars")
public class Car {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "model")
    public String model;

    @ColumnInfo(name = "brand")
    public String brand;

    @ColumnInfo(name = "year")
    public int year;

    public Car(String model, String brand, int year) {
        this.model = model;
        this.brand = brand;
        this.year = year;
    }

    @Ignore
    public Car() {

    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                '}';
    }
}
