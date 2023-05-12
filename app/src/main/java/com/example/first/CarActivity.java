package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.first.model.Car;
import com.example.first.repository.CarRepository;

import java.util.List;

public class CarActivity extends AppCompatActivity {

    EditText model, brand, year;
    Button insert;
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        model = findViewById(R.id.model);
        brand = findViewById(R.id.brand);
        year = findViewById(R.id.year);
        CarRepository carRepository = new CarRepository(this);

        insert = findViewById(R.id.insert);

        insert.setOnClickListener(v -> {
            carRepository.insert(new Car(model.getText().toString(), brand.getText().toString(), Integer.parseInt(year.getText().toString())));
        });

        show = findViewById(R.id.show);

        show.setOnClickListener(v -> {

            new Thread(() -> {
                List<Car> cars = carRepository.cars();
                Log.d("Car", cars.size()+"");
            }).start();

        });


    }
}