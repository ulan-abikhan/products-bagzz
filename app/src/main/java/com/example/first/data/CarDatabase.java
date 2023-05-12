package com.example.first.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.first.model.Car;

@Database(entities = {Car.class}, version =3)
public abstract class CarDatabase extends RoomDatabase {
    public abstract CarDAO carDao();

    public static CarDatabase carDatabase;

    public static synchronized CarDatabase getInstance(Context context) {

            carDatabase = Room.databaseBuilder(context,
                            CarDatabase.class, "CarsDB")
                    .fallbackToDestructiveMigration()
                    .build();

        return carDatabase;
    }

}
