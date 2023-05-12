package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.first.viewmodel.TestViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Test extends AppCompatActivity {

    FloatingActionButton fab;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        fab = findViewById(R.id.floatingActionButton);
        textView = findViewById(R.id.counter);


        TestViewModel testViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        testViewModel.getLiveData().observe(this, integer -> textView.setText(String.valueOf(integer)));

        fab.setOnClickListener(v -> {
            testViewModel.decreaseValue();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Destroy", "Destroy method called");
    }
}