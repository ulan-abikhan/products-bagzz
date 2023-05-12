package com.example.first.repository;

import android.content.Context;

import com.example.first.data.CarDatabase;
import com.example.first.model.Car;

import java.util.List;

public class CarRepository {

    private Context context;
    private CarDatabase carDatabase;

    public CarRepository(Context context) {
        this.context = context;
        carDatabase = CarDatabase.getInstance(context);
    }

    public void insert(Car car) {
        new Thread(() -> {
            carDatabase.carDao().insertAll(car);
        }).start();
    }

    public List<Car> cars() {
        return carDatabase.carDao().getAll();
    }

}
